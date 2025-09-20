package strategy;

public class BulkDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price * 0.3; // 30% discount for bulk purchases
    }
}
