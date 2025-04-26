package strategy;

public class RegularDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price * 0.1; // 10% discount
    }
}
