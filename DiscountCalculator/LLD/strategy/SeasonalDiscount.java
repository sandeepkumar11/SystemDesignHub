package strategy;

public class SeasonalDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price * 0.2; // 20% discount
    }
}
