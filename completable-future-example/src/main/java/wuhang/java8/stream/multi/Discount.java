package wuhang.java8.stream.multi;

import wuhang.java8.base.Shop;

public class Discount {
    public enum Code {
        None(0), Silver(5), Gold(10), Platinum(15), Diamond(20);
        
        private final int percentage;
        
        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    
    public static String applyDiscout(Quote quote) {
        return quote.getShopName() + " price is " + apply(quote.getPrice(),
                quote.getDiscountCode());
    }
    
    public static double apply(double price, Code code) {
        Shop.delay();
        return price * (100 - code.percentage) / 100;
    }
}
