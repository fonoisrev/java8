package wuhang.java8.stream.multi;

import wuhang.java8.base.Shop;
import wuhang.java8.stream.multi.Discount.Code;

import java.util.Random;

public class DiscountShop {
    Shop shop;
    Random random = new Random();
    
    public DiscountShop(Shop shop) {
        this.shop = shop;
    }
    
    public String getPrice(String product) {
        double price = shop.getPrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Code
                .values().length)];
        return String.format("%s:%.2f:%s", shop.getName(), price, code);
    }
    
}
