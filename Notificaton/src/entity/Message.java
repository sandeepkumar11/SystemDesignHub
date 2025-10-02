package entity;

import enums.ChannelType;
import enums.MessagePriority;

import java.time.Instant;
import java.util.UUID;

public class Message {
    private UUID messageId;
    private String recipient;
    private String subject;
    private String content;
    private MessagePriority priority;
    private ChannelType channelType;
    private Instant timestamp;

    // Getters
    public UUID getMessageId() {
        return messageId;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static class MessageBuilder {
        private UUID messageId;
        private String recipient;
        private String subject;
        private String content;
        private MessagePriority priority;
        private ChannelType channelType;
        private Instant timestamp;

        public MessageBuilder setMessageId(UUID messageId) {
            this.messageId = messageId;
            return this;
        }

        public MessageBuilder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public MessageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MessageBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder setPriority(MessagePriority priority) {
            this.priority = priority;
            return this;
        }

        public MessageBuilder setChannelType(ChannelType channelType) {
            this.channelType = channelType;
            return this;
        }

        public MessageBuilder setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.messageId = this.messageId != null ? this.messageId : UUID.randomUUID();
            message.recipient = this.recipient;
            message.subject = this.subject;
            message.content = this.content;
            message.priority = this.priority != null ? this.priority : MessagePriority.MEDIUM;
            message.channelType = this.channelType != null ? this.channelType : ChannelType.EMAIL;
            message.timestamp = this.timestamp != null ? this.timestamp : Instant.now();
            return message;
        }
    }
}
