package excercises.ch2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 15.02.2017.
 */

/*
10. Write a call to reduce that can be used to compute the average of a Stream<Double>.
Why can’t you simply compute the sum and divide by count()?
 */
public class Ex10 {
    public static void main(String[] args) {
        Stream<Double> stream = Stream.of(1.0, 2.0, 3.0, 4.0);
        AtomicInteger count = new AtomicInteger(0);
        double sum = stream.reduce(0.0, (x, y) -> { count.incrementAndGet(); return x + y; });
        System.out.println(sum / count.get());
    }
}
