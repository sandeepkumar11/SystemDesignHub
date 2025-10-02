package service.impl;

import channel.NotificationChannel;
import repository.ChannelRepository;
import service.ChannelService;

import java.util.List;

public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public List<NotificationChannel> getAllChannels() {
        return channelRepository.findAll();
    }

    @Override
    public NotificationChannel getChannelByName(String name) {
        return channelRepository.findByName(name);
    }

    @Override
    public void addChannel(NotificationChannel channel) {
        channelRepository.save(channel.getType(), channel);
    }

    @Override
    public void removeChannel(String name) {
        channelRepository.delete(name);
    }

    @Override
    public void updateChannel(String name, NotificationChannel updatedChannel) {
        NotificationChannel channel = getChannelByName(name);
        if (channel == null || !channel.getType().equals(updatedChannel.getType())) {
            throw new IllegalArgumentException("Channel with name " + name + " does not exist or type mismatch.");
        }
        channelRepository.save(name, updatedChannel);
    }
}
