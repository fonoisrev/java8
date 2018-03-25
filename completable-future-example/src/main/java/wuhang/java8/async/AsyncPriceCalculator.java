package wuhang.java8.async;

import wuhang.java8.base.Shop;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsyncPriceCalculator {
    
    // 异步方法
    public static Future<Double> getPriceAsync(Shop shop, String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = shop.getPrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }
    
    // 改进的异步方法
    public static Future<Double> getPriceAsync2(Shop shop, String product) {
        return CompletableFuture.supplyAsync(() -> shop.getPrice(product));
    }
}
