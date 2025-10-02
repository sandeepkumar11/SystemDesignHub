package coordinator;

import channel.NotificationChannel;
import entity.Message;
import enums.ChannelType;
import strategy.ChannelStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotificationCoordinator {

    private final ChannelStrategy channelStrategy;

    public NotificationCoordinator(ChannelStrategy channelStrategy) {
        this.channelStrategy = channelStrategy;
    }

    // @KafkaListener(topics = "notification-high")
    public void processHighPriorityMessage(Message message) {
        processWithRetry(message, 3);
    }

    // @KafkaListener(topics = "notification-medium")
    public void processMediumPriorityMessage(Message message) {
        processWithRetry(message, 2);
    }

    // @KafkaListener(topics = "notification-low")
    public void processLowPriorityMessage(Message message) {
        processWithRetry(message, 1);
    }

    private void processWithRetry(Message message, int maxRetries) {
        int attempts = 0;
        boolean success = false;
        while (attempts < maxRetries && !success) {
            try {
                NotificationChannel channel = channelStrategy.getChannel(message);
                if (channel != null) {
                    channel.send(message);
                    success = true;
                } else {
                    System.err.println("No channel found for type: " + message.getChannelType());
                    break;
                }
            } catch (Exception e) {
                attempts++;
                System.err.println("Failed to send message. Attempt " + attempts + " of " + maxRetries);
                if (attempts == maxRetries) {
                    System.err.println("Max retries reached. Logging failure for message: " + message);
                    // Log failure to DB or monitoring system
                }
            }
        }
    }

}
