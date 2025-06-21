package deadMessages;

import publisher.Message;
import queue.LinkedQueue;
import queue.Queue;
import subscriber.Subscriber;

public class DeadLetterQueue implements DeadLetterHandler{
    private final Queue<DeadLetterMessage> queue;

    public DeadLetterQueue() {
        this.queue = new LinkedQueue<>();
    }

    @Override
    public void handle(Message message, Subscriber subscriber, String failureReason) {
        if (message == null || subscriber == null || failureReason == null) {
            throw new IllegalArgumentException("publisher.Message, subscriber.Subscriber, and failure reason cannot be null");
        }
        DeadLetterMessage deadLetterMessage = new DeadLetterMessage(message, subscriber, failureReason);
        queue.enqueue(deadLetterMessage);
        System.out.println("Dead letter message added: " + deadLetterMessage);
    }


}
