package exercises.ch6;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by y.dovganich on 31.03.2017.
 */
/*
3. Generate 1,000 threads, each of which increments a counter 100,000 times.
Compare the performance of using AtomicLong versus LongAdder.
 */
public class Ex3 {
    public static void main(String[] args) {
        LongAdder counter1 = new LongAdder();
        perform(1000, counter1::increment, counter1::longValue, "LongAdder");

        AtomicLong counter2 = new AtomicLong();
        perform(1000, counter2::incrementAndGet, counter2::longValue, "AtomicLong");
    }

    private static void perform(int quantity, Runnable increment, Supplier<Long> getValue, String title) {
        ExecutorService executor  = Executors.newFixedThreadPool(quantity);

        List<Callable<Integer>> tasks = getCommand(quantity, increment);

        long start = System.currentTimeMillis();

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(100_000_000, getValue.get().longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("%s: %d%n", title, System.currentTimeMillis() - start);
        executor.shutdown();
    }

    private static List<Callable<Integer>> getCommand(int quantity, Runnable increment) {
        Callable<Integer> command = () -> {
            IntStream.range(0, 100_000).forEach(__ -> increment.run());
            return 0;
        };
        return IntStream
                .range(0, quantity)
                .mapToObj(__ -> command)
                .collect(Collectors.toList());
    }
}
