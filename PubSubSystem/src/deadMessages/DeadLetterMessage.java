package deadMessages;

import publisher.Message;
import subscriber.Subscriber;

public class DeadLetterMessage {
    private final Message message;
    private final Subscriber subscriber;
    private final String failureReason;
    private final long timestamp;

    public DeadLetterMessage(Message message, Subscriber subscriber, String failureReason) {
        this.message = message;
        this.subscriber = subscriber;
        this.failureReason = failureReason;
        this.timestamp = System.currentTimeMillis();
    }

    public Message getMessage() {
        return message;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "deadMessages.DeadLetterMessage{" +
                "message=" + message +
                ", subscriber=" + subscriber +
                ", failureReason='" + failureReason + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
