package service.impl;

import channel.impl.KafkaChannel;
import dto.request.NotificationRequest;
import entity.Message;
import service.NotificationService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class NotificationServiceImpl implements NotificationService {
    private final KafkaChannel kafkaChannel;

    public NotificationServiceImpl(KafkaChannel kafkaChannel) {
        this.kafkaChannel = kafkaChannel;
    }

    @Override
    public void notify(NotificationRequest request) {
        validateRequest(request);

        Message message = request.getMessage();
//        message.setMessageId(UUID.randomUUID());
//        message.setTimestamp(Instant.now());
        System.out.println("Sending notification via KafkaChannel: " + message.getMessageId());
        kafkaChannel.notify(message);
    }

    @Override
    public void notifyBulk(List<NotificationRequest> requests) {
        for (NotificationRequest request : requests) {
            try {
                notify(request);
            } catch (IllegalArgumentException e) {
                System.err.println("Failed to send notification: " + e.getMessage());
            }
        }
    }

    private void validateRequest(NotificationRequest request) {
        if (request.getMessage() == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        if(request.getMessage().getRecipient() == null || request.getMessage().getRecipient().isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be null or empty");
        }
    }
}
