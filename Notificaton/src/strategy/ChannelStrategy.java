package strategy;

import channel.NotificationChannel;
import entity.Message;

public interface ChannelStrategy {

    NotificationChannel getChannel(String channelType);

    NotificationChannel getChannel(Message message);

}
