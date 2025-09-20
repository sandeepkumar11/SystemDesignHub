import deadMessages.DeadLetterHandler;
import deadMessages.DeadLetterQueue;
import publisher.DependencyManager;
import publisher.Message;
import publisher.Publisher;
import queue.LinkedQueue;
import queue.Queue;
import retry.RetryHandler;
import retry.RetryStrategy;
import subscriber.RegexSubscriber;
import subscriber.Subscriber;

import java.util.List;

public class PubSubSystem {
    public void initPubSub() throws InterruptedException {
        System.out.println("PubSub system initialized...");

        // Initialize the message queue, dependency manager, retry strategy, and dead letter handler
        Queue<Message> messageQueue = new LinkedQueue<>();
        DependencyManager dependencyManager = new DependencyManager();
        RetryStrategy retryStrategy = new RetryHandler(3,1000);
        DeadLetterHandler deadLetterHandler = new DeadLetterQueue();

        Publisher publisher = Publisher.getInstance(messageQueue, dependencyManager, retryStrategy, deadLetterHandler);

        // Example of subscribing a subscriber
        Subscriber subscriber1 = new RegexSubscriber("order.*");
        Thread.sleep(1000); // Simulating some delay for demonstration
        Subscriber subscriber2 = new RegexSubscriber("payment.*");
        Thread.sleep(2000);
        Subscriber subscriber3 = new RegexSubscriber("order.*");
        Subscriber subscriber4 = new RegexSubscriber(".*");
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);
        publisher.subscribe(subscriber4);

        publisher.addDependency(subscriber3, List.of(subscriber1));

        // Example of publishing a message
        Message message1 = new Message(1,"order.created", "Order created successfully");
        Message message2 = new Message(2,"payment.completed", "Payment completed successfully");
        publisher.publish(message1);
        publisher.publish(message2);

    }
}
