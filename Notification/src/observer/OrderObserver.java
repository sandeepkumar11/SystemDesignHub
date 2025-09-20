package observer;

import event.OrderEvent;

public interface OrderObserver {
    void update(OrderEvent event);
}
