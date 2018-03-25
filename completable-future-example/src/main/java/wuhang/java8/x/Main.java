package wuhang.java8.x;

import wuhang.java8.base.Shops;
import wuhang.java8.stream.multi.Discount;
import wuhang.java8.stream.multi.DiscountShop;
import wuhang.java8.stream.multi.Quote;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    
    public static void main(String[] args) {
        long start = System.nanoTime();
    
        CompletableFuture[] futures = findPricesStream("myProducts")
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in" +
                        " " + ((System.nanoTime() - start) / 1_000_000) + " " +
                        "msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + (System
                .nanoTime() - start) / 1_000_000 + " msecs");
    }
    
    // 无 collect to list 实现
    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return Shops.shops.stream()
                .map(DiscountShop::new)
                .map(discountShop -> CompletableFuture.supplyAsync(()
                        -> discountShop.getPrice(product)))
                .map(stringCompletableFuture ->
                        stringCompletableFuture.thenApply(Quote::parse))
                .map(quoteCompletableFuture -> quoteCompletableFuture
                        .thenCompose(quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscout(quote))));
    }
}
