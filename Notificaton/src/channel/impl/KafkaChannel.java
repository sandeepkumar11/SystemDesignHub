package channel.impl;

import entity.Message;

public class KafkaChannel {

//    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void notify(Message message){
        String topic = switch (message.getPriority()) {
            case HIGH -> "notification-high";
            case MEDIUM -> "notification-medium";
            case LOW -> "notification-low";
            default -> "notification-common";
        };

//        kafkaTemplate.send(topic, message.getId().toString(), message)
//        .addCallback(
//                result -> System.out.println("Message sent to topic: " + topic),
//                ex -> System.err.println("Failed to send message to topic: " + topic + ", error: " + ex.getMessage())
//        );
    }
}
