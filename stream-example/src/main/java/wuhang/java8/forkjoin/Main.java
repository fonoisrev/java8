package wuhang.java8.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        long fork = findDuration(Main::forkJoinSum, 1000_0000);
        System.out.println("fork duration is  " + fork + " nanoSeconds");
        long nonfork = findDuration(Main::nonForkJoinSum, 1000_0000);
        System.out.println("nonfork duration is  " + nonfork + " nanoSeconds");
    }
    
    public static long findDuration(Function<Long, Long> adder, long n) {
        long faster = Long.MAX_VALUE;
        for (int i = 0; i < 3; ++i) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = System.nanoTime() - start;
            System.out.println("result is " + sum);
            if (faster > duration) {
                faster = duration;
            }
        }
        return faster;
    }
    
    private static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        
        return new ForkJoinPool().invoke(task);
    }
    
    private static long nonForkJoinSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0, Long::sum);
    }
}

class ForkJoinSumCalculator extends RecursiveTask<Long> {
    
    private long[] numbers;
    private int start;
    private int end;
    
    public static final long THRESHOLD = 1_0000L;
    
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    
    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,
                start, start + length / 2);
        leftTask.fork();
        
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,
                start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
        
    }
    
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; ++i) {
            sum += numbers[i];
        }
        return sum;
    }
}
