package subject;

import event.OrderEvent;
import observer.OrderObserver;

public interface OrderSubject {
    void registerObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);
    void notifyObservers(OrderEvent event);
}
