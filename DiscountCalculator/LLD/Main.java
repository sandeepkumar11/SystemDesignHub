import strategy.BulkDiscount;
import strategy.DiscountStrategy;
import strategy.RegularDiscount;
import strategy.SeasonalDiscount;

public class Main {
    public static void main(String[] args) {
        double price = 100.0;

        // Using different discount strategies
        DiscountStrategy regularDiscount = new RegularDiscount();
        System.out.println("Regular Discount: " + regularDiscount.calculateDiscount(price));

        DiscountStrategy seasonalDiscount = new SeasonalDiscount();
        System.out.println("Seasonal Discount: " + seasonalDiscount.calculateDiscount(price));

        DiscountStrategy bulkDiscount = new BulkDiscount();
        System.out.println("Bulk Discount: " + bulkDiscount.calculateDiscount(price));
    }
}
