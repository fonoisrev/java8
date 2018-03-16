package wuhang.java8.spliterator;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        String toDo = "Spring Boot is designed to get you up and running as " +
                "quickly as possible, with minimal upfront configuration of " +
                "Spring. Spring Boot takes an opinionated view of building " +
                "production ready applications. Built directly on Spring " +
                "Boot's innovative approach to enterprise Java, Spring Cloud " +
                "simplifies distributed, microservice-style architecture by " +
                "implementing proven patterns to bring resilience, " +
                "reliability, and coordination to your microservices. Connect" +
                " the Enterprise to the Internet of Anything-mobile devices, " +
                "sensors, wearables, automobiles, and more. Spring Cloud Data" +
                " Flow provides a unified service for creating composable " +
                "data microservices that address streaming and ETL-based data" +
                " processing patterns. ";
    
        Stream<Character> wordsStream = StreamSupport.stream(new WordCountSpliterator(toDo), true);
        int count = wordsStream.reduce(new WordCounter(0), WordCounter::wordCounter,
                WordCounter::combine).getCount();
        System.out.println(count);
    }
}
