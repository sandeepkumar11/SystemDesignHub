package repository;

import channel.NotificationChannel;
import enums.ChannelType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelRepository {
    Map<String, NotificationChannel> channelMap;

    public ChannelRepository() {
        this.channelMap = new ConcurrentHashMap<>();
    }

    public void save(String type, NotificationChannel channel) {
        channelMap.put(type, channel);
    }

    public NotificationChannel findByName(String type) {
        return channelMap.get(type);
    }

    public void delete(String type) {
        channelMap.remove(type);
    }

    public List<NotificationChannel> findAll() {
        return channelMap.values().stream().toList();
    }
}
