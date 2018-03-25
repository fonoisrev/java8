package wuhang.java8.base;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    Random random = new Random();
    private String name;
    
    public Shop(String name) {
        this.name = name;
    }
    
    // 同步方法
    public double getPrice(String product) {
        return calculatePrice(product);
    }
    
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    
    
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getName() {
        return name;
    }
}
