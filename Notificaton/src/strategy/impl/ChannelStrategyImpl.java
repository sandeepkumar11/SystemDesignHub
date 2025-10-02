package strategy.impl;

import channel.NotificationChannel;
import entity.Message;
import enums.ChannelType;
import strategy.ChannelStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChannelStrategyImpl implements ChannelStrategy {

    private final Map<ChannelType, NotificationChannel> channels;

    public ChannelStrategyImpl(List<NotificationChannel> channelList) {
        this.channels = channelList.stream()
                .collect(Collectors.toMap(
                        channel -> ChannelType.valueOf(channel.getType()),
                        channel -> channel));

    }

    @Override
    public NotificationChannel getChannel(String channelType) {
        return channels.get(ChannelType.valueOf(channelType));
    }

    @Override
    public NotificationChannel getChannel(Message message) {
        return getChannel(message.getChannelType().toString());
    }

    @Override
    public void addChannel(NotificationChannel channel) {
        channels.putIfAbsent(ChannelType.valueOf(channel.getType()), channel);
    }
}
