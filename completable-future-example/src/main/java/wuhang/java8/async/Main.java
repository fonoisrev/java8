package wuhang.java8.async;

import wuhang.java8.async.AsyncPriceCalculator;
import wuhang.java8.base.Shop;

import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop("First Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = AsyncPriceCalculator.getPriceAsync(shop,
                "my favorite product");
        long invacationNanoTimes = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after" + invacationNanoTimes +" msecs");
    
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f %n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        long retrivalTeime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrivalTeime + " msecs");
    }
}
