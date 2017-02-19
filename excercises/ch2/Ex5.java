package excercises.ch2;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 13.02.2017.
 */

/*
5. Using Stream.iterate, make an infinite stream of random numbers—not by
calling Math.random but by directly implementing a linear congruential generator.
In such a generator, you start with x0 = seed and then produce xn + 1 =
(a xn + c) % m, for appropriate values of a, c, and m. You should implement a
method with parameters a, c, m, and seed that yields a Stream<Long>. Try out a =
25214903917, c = 11, and m = 2^48.
 */
public class Ex5 {
    public static void main(String[] args) {
        Stream<Long> longStream = randomNumbers(25214903917L, 11, (long) Math.pow(2.0, 48.0), 0);
        longStream
                .limit(10)
                .mapToLong(Long::longValue)
                .forEach(System.out::println);

    }

    static Stream<Long> randomNumbers(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }
}
