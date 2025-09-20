package subject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import event.OrderEvent;
import observer.OrderObserver;

public class OrderService implements OrderSubject{
    private final List<OrderObserver>observers = new ArrayList<>();
    private  final ConcurrentHashMap<Long,OrderEvent> eventCache = new ConcurrentHashMap<>();

    @Override
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(OrderEvent event) {
        for(OrderObserver observer : observers){
            observer.update(event);
        }
        eventCache.putIfAbsent(event.getOrderId(), event);
    }

    public void placeOrder(Long orderId, Long customerId, String customerEmail, boolean isUrgent) {
        OrderEvent event = new OrderEvent("ORDER_PLACED", orderId, customerId, customerEmail, isUrgent);
        notifyObservers(event);
    }
    
}
