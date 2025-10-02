package dto.request;

import entity.Message;

public class NotificationRequest {
    private final Message message;
    private final String requesterId;
    private final String idempotencyKey; // prevent duplicate processing

    public NotificationRequest(Message message, String requesterId, String idempotencyKey) {
        this.message = message;
        this.requesterId = requesterId;
        this.idempotencyKey = idempotencyKey;
    }

    public Message getMessage() {
        return message;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }
}
