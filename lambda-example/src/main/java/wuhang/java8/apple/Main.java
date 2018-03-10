package wuhang.java8.apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 152));
        apples.add(new Apple("green", 148));
        apples.add(new Apple("red", 152));
        apples.add(new Apple("red", 148));
        
        // lambda
        // predicate Apple -> boolean
        Predicate<Apple> greenApple = apple -> apple.getColor().equals("green");
        Predicate<Apple> heavyApple = apple -> apple.getWeight() > 150;
        List<Apple> apples1 = filterApple(apples, greenApple);
        System.out.println(apples1);
        
        // 复合lambda表达式
        List<Apple> apples2 = filterApple(apples, greenApple.and(heavyApple));
        System.out.println(apples2);
        List<Apple> apples3 = filterApple(apples, greenApple.and(heavyApple)
                .negate());
        System.out.println(apples3);
        
//        // function Apple,color -> boolean
//        BiFunction<Apple, String, Boolean> colorApple = (apple, color) -> apple
//                .getColor().equals(color);
//        BiFunction<Apple, Integer, Boolean> weightApple = (apple, weight) ->
//                apple.getWeight() > weight;
    
    }
    
    
    public static <T> List<T> filterApple(List<T> allApples,
                                          Predicate<T> predicate) {
        return allApples.stream().filter(predicate::test).collect(Collectors
                .toList());
    }
    
//    public static <T, R> List<R> filterApple2(List<T> allApples, Function<T,
//            R> func) {
//        return allApples.stream().map(func).collect(Collectors.toList());
//    }
}
