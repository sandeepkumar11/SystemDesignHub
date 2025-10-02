package channel.impl;

import channel.NotificationChannel;
import entity.Message;
import enums.ChannelType;
import helper.TemplateEngine;
import provides.Provider;

public class SmsChannel implements NotificationChannel {
    private final Provider provider;
    private final TemplateEngine templateEngine;

    public SmsChannel(Provider provider, TemplateEngine templateEngine) {
        this.provider = provider;
        this.templateEngine = templateEngine;
    }

    @Override
    public void send(Message message) {
        String content = templateEngine.process(message);
        provider.send(message.getRecipient(), message.getSubject(), content);
    }

    @Override
    public boolean support(ChannelType channelType) {
        return ChannelType.SMS.equals(channelType);
    }

    @Override
    public String getType() {
        return ChannelType.SMS.name();
    }
}
