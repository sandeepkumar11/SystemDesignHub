package strategy.impl;

import channel.NotificationChannel;
import entity.Message;
import enums.ChannelType;
import service.ChannelService;
import strategy.ChannelStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChannelStrategyImpl implements ChannelStrategy {

    private final ChannelService channelService;

    public ChannelStrategyImpl(ChannelService channelService) {
        this.channelService = channelService;

    }

    @Override
    public NotificationChannel getChannel(String channelType) {
        return getChannels().get(ChannelType.valueOf(channelType));
    }

    @Override
    public NotificationChannel getChannel(Message message) {
        return getChannel(message.getChannelType().toString());
    }

    private Map<ChannelType, NotificationChannel> getChannels() {
        List<NotificationChannel> channelList = channelService.getAllChannels();
        return channelList.stream().collect(Collectors.toMap(
                ch -> ChannelType.valueOf(ch.getType()),
                ch -> ch
        ));
    }
}
