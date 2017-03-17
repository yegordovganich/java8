package exercises.ch2;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 14.02.2017.
 */

/*
7. Your manager asks you to write a method public static <T> boolean
isFinite(Stream<T> stream). Why isn’t that such a good idea? Go ahead and
write it anyway.
 */
public class Ex7 {
    public static void main(String[] args) {
        Stream<String> s1 = Stream.of("1", "2");
        System.out.println(isFinite(s1));
        Stream<Double> s2 = Stream.generate(Math::random);
        System.out.println(isFinite(s2));
        Stream<BigInteger> s3 = Stream.iterate(BigInteger.ZERO, x -> x.add(BigInteger.ONE));
        System.out.println(isFinite(s3));
    }

    public static <T> boolean isFinite(Stream<T> stream) {
        // a bit tricky...
        return true;
    }
}
