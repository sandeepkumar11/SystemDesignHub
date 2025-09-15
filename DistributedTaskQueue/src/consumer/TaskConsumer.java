package consumer;

import helper.TaskStore;
import model.Task;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskConsumer implements Consumer {
    private final TaskStore taskStore;
    private final Set<String> subscribedTopics;
    private final ExecutorService executor;
    private final AtomicBoolean isRunning;

    public TaskConsumer(TaskStore taskStore) {
        this.taskStore = taskStore;
        this.subscribedTopics = ConcurrentHashMap.newKeySet();
        this.executor = Executors.newCachedThreadPool();
        this.isRunning = new AtomicBoolean(true);
    }

    @Override
    public void subscribe(String topic) {
        if (!isRunning.get()) {
            throw new IllegalStateException("Consumer is closed");
        }
        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null/empty");
        }
        subscribedTopics.add(topic);
        taskStore.createTopic(topic);
    }

    @Override
    public void unsubscribe(String topic) {
        if (!isRunning.get()) {
            throw new IllegalStateException("Consumer is closed");
        }
        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null/empty");
        }
        subscribedTopics.remove(topic);
    }

    @Override
    public CompletableFuture<List<Task>> poll(String topic, long timeout, int maxTasks) {
        if (!isRunning.get()) {
            return CompletableFuture.failedFuture(new IllegalStateException("Consumer is closed"));
        }
        if (!subscribedTopics.contains(topic)) {
            return CompletableFuture.failedFuture(new IllegalStateException("Not subscribed to topic: " + topic));
        }
        if (timeout < 0 || maxTasks <= 0) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Invalid timeout or maxTasks"));
        }
        return CompletableFuture.supplyAsync(() -> {
            try {
                return taskStore.pollTasks(topic, timeout, maxTasks);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return Collections.emptyList();
            }
        }, executor);
    }

    @Override
    public void close() {
        isRunning.set(false);
        subscribedTopics.clear();
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
