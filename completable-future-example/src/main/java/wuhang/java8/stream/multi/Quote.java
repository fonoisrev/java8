package wuhang.java8.stream.multi;

import wuhang.java8.stream.multi.Discount.Code;

public class Quote {
    private String shopName;
    private double price;
    private Code discountCode;
    
    public static Quote parse(String s) {
        String[] splits = s.split(":");
        String name = splits[0];
        double price = Double.parseDouble(splits[1]);
        Code discount = Discount.Code.valueOf(splits[2]);
        return new Quote(name, price, discount);
    }
    
    public Quote(String shopName, double price, Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }
    
    public String getShopName() {
        return shopName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public Code getDiscountCode() {
        return discountCode;
    }
}
