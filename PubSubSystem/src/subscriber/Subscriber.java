package subscriber;

import publisher.Message;

public interface Subscriber {
    boolean matches(Message message);
    void onMessage(Message message);
}
