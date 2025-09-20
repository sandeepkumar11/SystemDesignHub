import channels.EmailNotificationService;
import channels.SMSNotificationService;
import subject.OrderService;

public class NotificationService {
    public static void main(String[] args){
        System.out.println("Welcome to the Notification Service!");

        OrderService orderService = new OrderService();
        orderService.registerObserver(new EmailNotificationService());
        orderService.registerObserver(new SMSNotificationService());

        orderService.placeOrder(1L, 100L, "alice@example.com", false); // Email only
        orderService.placeOrder(2L, 101L, "bob@example.com", true);    // Email + SMS
    }
}
