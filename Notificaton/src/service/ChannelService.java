package service;

import channel.NotificationChannel;

import java.util.List;

public interface ChannelService {
    List<NotificationChannel> getAllChannels();

    NotificationChannel getChannelByName(String name);

    void addChannel(NotificationChannel channel);

    void removeChannel(String name);

    void updateChannel(String name, NotificationChannel updatedChannel);

}
