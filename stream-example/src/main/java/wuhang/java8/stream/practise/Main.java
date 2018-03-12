package wuhang.java8.stream.practise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambrige");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambrige");
        Trader brian = new Trader("Brian", "Cambrige");
        
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        
        // 1.找出2011年发生的所有交易，并按交易额排序
        System.out.println(transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList())
        );
        
        // 2.交易员都在哪些城市工作过
        System.out.println(
                transactions.stream()
                        .map(Transaction::getTrader)
                        .map(Trader::getCity)
                        .distinct()
                        .collect(Collectors.toList())
        );
        
        // 3. 查找所有来自于剑桥的交易员
        System.out.println(
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> "Cambrige".equals(trader.getCity()))
                        .map(Trader::getName)
                        .collect(Collectors.toList())
        );
        
        // 4. 返回所有交易员名字的字符串，并按字母顺序排序
        System.out.println(
                transactions.stream()
                        .map(Transaction::getTrader)
                        .map(Trader::getName)
                        .distinct()
                        .sorted(String::compareTo)
                        .collect(Collectors.toList())
        );
        
        // 5.有没有交易员是在Milan工作的
        System.out.println(
                transactions.stream()
                        .anyMatch(transaction -> "Milan".equals(transaction
                                .getTrader().getCity()))
        );
        
        // 6. 打印生活在Cambrige的交易员的所有交易额
        System.out.println(
                transactions.stream()
                        .filter(transaction -> "Cambrige".equals(transaction
                                .getTrader().getCity()))
                        .map(Transaction::getValue)
                        .reduce(0, (a, b) -> a + b)
                        .intValue()
        );
        
        // 7.所有交易中
        System.out.println(
                transactions.stream()
                        .map(Transaction::getValue)
                        .max(Integer::compareTo)
                        .get()
        );
        
        // 8. 找到交易额最小的交易
        System.out.println(
                transactions.stream()
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .findFirst()
                        .get()
        );
    }
}
