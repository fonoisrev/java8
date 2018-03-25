package wuhang.java8.stream;

import wuhang.java8.base.Shop;
import wuhang.java8.base.Shops;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main2 {
    static List<Shop> shops = Shops.shops;
    
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
        
        start = System.nanoTime();
        System.out.println(findPrices2("myPhone27s"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
        
        start = System.nanoTime();
        System.out.println(findPrices3("myPhone27s"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
        
    }
    
    // 同步流
    public static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName()
                        , shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    
    // 并行流
    public static List<String> findPrices2(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName()
                        , shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    
    public static List<String> findPrices3(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() ->
                                String.format("%s price is %5.2f", shop
                                        .getName(), shop.getPrice(product))))
                        .collect(Collectors.toList());
        return priceFutures.parallelStream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    
}
