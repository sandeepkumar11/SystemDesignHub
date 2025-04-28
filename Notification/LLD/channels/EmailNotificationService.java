package channels;

import event.OrderEvent;
import observer.OrderObserver;

public class EmailNotificationService implements OrderObserver{

    @Override
    public void update(OrderEvent event) {
        if (event.getCustomerEmail() != null && event.getEventType().equals("ORDER_PLACED")) {
            sendEmail(event.getCustomerEmail(), "Order " + event.getOrderId() + " placed successfully!");
        }
    }

    private void sendEmail(String email, String message) {
        // Simulate email sending
        System.out.println("Email to " + email + ": " + message);
    }

}
