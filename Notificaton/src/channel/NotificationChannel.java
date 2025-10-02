package channel;

import entity.Message;
import enums.ChannelType;

public interface NotificationChannel {

    void send(Message message);

    boolean support(ChannelType channelType);

    String getType();
}
