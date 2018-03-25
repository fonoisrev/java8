package wuhang.java8.stream.multi;

import wuhang.java8.base.Shops;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
    
        start = System.nanoTime();
        System.out.println(findPrices2("myPhone27s"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
    }
    
    // 基本实现
    public static List<String> findPrices(String product) {
        return Shops.shops.stream()
                .map(DiscountShop::new)
                .map(discountShop -> discountShop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscout)
                .collect(Collectors.toList());
        
    }
    
    // CompletabelFuture 实现
    public static List<String> findPrices2(String product) {
        List<CompletableFuture<String>> priceFuture =
                Shops.shops.stream()
                        .map(DiscountShop::new)
                        .map(discountShop -> CompletableFuture.supplyAsync(()
                                -> discountShop.getPrice(product)))
                        .map(stringCompletableFuture ->
                                stringCompletableFuture.thenApply(Quote::parse))
                        .map(quoteCompletableFuture -> quoteCompletableFuture
                                .thenCompose(quote -> CompletableFuture
                                        .supplyAsync(() -> Discount
                                                .applyDiscout(quote))))
                        .collect(Collectors.toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
