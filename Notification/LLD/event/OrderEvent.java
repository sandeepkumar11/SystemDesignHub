package event;

public class OrderEvent {
    private final String eventType; // e.g., "ORDER_PLACED", "ORDER_SHIPPED"
    private final Long orderId;
    private final Long customerId;
    private final String customerEmail;
    private final boolean isUrgent;

    public OrderEvent(String eventType, Long orderId, Long customerId, String customerEmail, boolean isUrgent) {
        this.eventType = eventType;
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.isUrgent = isUrgent;
    }

    public String getEventType() { return eventType; }
    public Long getOrderId() { return orderId; }
    public Long getCustomerId() { return customerId; }
    public String getCustomerEmail() { return customerEmail; }
    public boolean isUrgent() { return isUrgent; }
}
