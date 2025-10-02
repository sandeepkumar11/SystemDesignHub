import channel.NotificationChannel;
import channel.impl.EmailChannel;
import channel.impl.PushChannel;
import channel.impl.SmsChannel;
import coordinator.NotificationCoordinator;
import entity.Message;
import enums.ChannelType;
import enums.MessagePriority;
import helper.TemplateEngine;
import provides.impl.EmailProvider;
import provides.impl.PushProvider;
import provides.impl.SmsProvider;
import strategy.ChannelStrategy;
import strategy.impl.ChannelStrategyImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Notification System!");

        TemplateEngine templateEngine = new TemplateEngine();
        NotificationChannel emailChannel = new EmailChannel(new EmailProvider(), templateEngine);
        NotificationChannel smsChannel = new SmsChannel(new SmsProvider(), templateEngine);
        NotificationChannel pushChannel = new PushChannel(new PushProvider(), templateEngine);

        ChannelStrategy channelStrategy = new ChannelStrategyImpl(List.of(
                emailChannel, smsChannel, pushChannel
        ));

        NotificationCoordinator coordinator = new NotificationCoordinator( channelStrategy
        );

        Message highPriorityMessage = new Message.MessageBuilder()
                .setRecipient("high@example.com")
                .setSubject("Urgent Notification")
                .setContent("High priority message")
                .setChannelType(ChannelType.EMAIL)
                .setPriority(MessagePriority.HIGH)
                .build();

        Message mediumPriorityMessage = new Message.MessageBuilder()
                .setRecipient("medium@example.com")
                .setSubject("Regular Update")
                .setContent("Medium priority message")
                .setChannelType(ChannelType.PUSH)
                .setPriority(MessagePriority.MEDIUM)
                .build();

        Message lowPriorityMessage = new Message.MessageBuilder()
                .setRecipient("low@example.com")
                .setSubject("Casual Info")
                .setContent("Low priority message")
                .setChannelType(ChannelType.SMS)
                .setPriority(MessagePriority.LOW)
                .build();

        coordinator.processHighPriorityMessage(highPriorityMessage);
        coordinator.processMediumPriorityMessage(mediumPriorityMessage);
        coordinator.processLowPriorityMessage(lowPriorityMessage);
    }
}