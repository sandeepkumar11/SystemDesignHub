package channels;

import event.OrderEvent;
import observer.OrderObserver;

public class SMSNotificationService implements OrderObserver {
    @Override
    public void update(OrderEvent event) {
        if (event.isUrgent() && event.getEventType().equals("ORDER_PLACED")) {
            sendSMS(event.getCustomerId(), "Urgent order " + event.getOrderId() + " placed!");
        }
    }

    private void sendSMS(Long customerId, String message) {
        // Simulate SMS sending
        System.out.println("SMS to customer " + customerId + ": " + message);
    }
}
