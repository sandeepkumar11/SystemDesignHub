package deadMessages;

import publisher.Message;
import subscriber.Subscriber;

@FunctionalInterface
public interface DeadLetterHandler {
    void handle(Message message, Subscriber subscriber, String failureReason) throws IllegalArgumentException;
}
