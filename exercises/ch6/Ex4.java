package exercises.ch6;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * Created by y.dovganich on 31.03.2017.
 */
/*
4. Use a LongAccumulator to compute the maximum or minimum of the
accumulated elements.
 */
public class Ex4 {
    public static void main(String[] args) {
        final int MAX_VALUE = 1_000;
        LongAccumulator maxAcc = new LongAccumulator(Long::max, 0);
        LongAccumulator minAcc = new LongAccumulator(Long::min, MAX_VALUE);

        IntStream.range(0, 100)
            .forEach(__ ->
                new Thread(() -> {
                    long value = (long) (Math.random() * MAX_VALUE);
                    maxAcc.accumulate(value);
                    minAcc.accumulate(value);
                }).start()
            );

        System.out.println("max: " + maxAcc.get());
        System.out.println("min: " + minAcc.get());
    }
}
