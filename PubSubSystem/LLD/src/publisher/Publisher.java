package publisher;

import deadMessages.DeadLetterHandler;
import queue.Queue;
import retry.RetryResult;
import retry.RetryStrategy;
import subscriber.Subscriber;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Publisher {
    private final Queue<Message> queue;
    private final List<Subscriber> subscribers;
    private final DependencyManager dependencyManager;
    private final RetryStrategy retry;
    private final DeadLetterHandler deadLetterHandler;
    private static volatile Publisher instance;

    private Publisher(Queue<Message> queue, DependencyManager dependencyManager, RetryStrategy retryStrategy, DeadLetterHandler deadLetterHandler) {
        if (queue == null || dependencyManager == null || retryStrategy == null || deadLetterHandler == null) {
            throw new IllegalArgumentException("queue.Queue, publisher.DependencyManager, retry.RetryStrategy, and deadMessages.DeadLetterHandler cannot be null");
        }
        this.queue = queue;
        this.subscribers = new CopyOnWriteArrayList<>();
        this.dependencyManager = dependencyManager;
        this.retry = retryStrategy;
        this.deadLetterHandler = deadLetterHandler;
    }

    public static Publisher getInstance(Queue<Message> queue, DependencyManager dependencyManager, RetryStrategy retryStrategy, DeadLetterHandler deadLetterHandler) {
        if (instance == null) {
            synchronized (Publisher.class) {
                if (instance == null) {
                    instance = new Publisher(queue, dependencyManager, retryStrategy, deadLetterHandler);
                }
            }
        }
        return instance;
    }

    public void subscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber.Subscriber cannot be null");
        }
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber.Subscriber cannot be null");
        }
        subscribers.remove(subscriber);
    }

    public void addDependency(Subscriber dependent, Collection<Subscriber> dependencies) {
        if (dependent == null || dependencies == null || dependencies.isEmpty()) {
            throw new IllegalArgumentException("Dependent and dependencies cannot be null or empty");
        }
        for (Subscriber dependency : dependencies) {
            if (dependency == null) {
                throw new IllegalArgumentException("Dependency cannot be null");
            }
            dependencyManager.addDependency(dependent, dependency);
        }
    }

    public void publish(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        queue.enqueue(message);
        notifySubscribers(message);
    }

    private void notifySubscribers(Message message) {
        List<Subscriber> matching = subscribers.stream()
                .filter(subscriber -> subscriber.matches(message))
                .collect(Collectors.toList());
        List<Subscriber> orderedSubscribers = dependencyManager.getOrderedSubscribers(matching);
        Set<Subscriber> visited = new HashSet<>();
        for (Subscriber subscriber : orderedSubscribers) {
            if (visited.contains(subscriber)) {
                continue; // Skip already notified subscriber
            }
            visited.add(subscriber);
            RetryResult retryResult = retry.execute(() -> subscriber.onMessage(message));
            if (!retryResult.isSuccess()) {
                System.err.println("Failed to deliver message to subscriber: " + subscriber);
                deadLetterHandler.handle(message,subscriber,retryResult.getFailureReason());
            }
        }
    }

    public Message getNextMessage() {
        return (Message) queue.dequeue();
    }

    public boolean hasMessages() {
        return !queue.isEmpty();
    }
}
