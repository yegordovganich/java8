package exercises.ch2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 13.02.2017.
 */

/*
4. Suppose you have an array int[] values = { 1, 4, 9, 16 }. What is
        Stream.of(values)? How do you get a stream of int instead?
*/
public class Ex4 {
    public static void main(String[] args) {
        int[] values = {1, 4, 9, 16};
        Stream<int[]> values1 = Stream.of(values); // Stream<int[]> !!!

        IntStream values2 = IntStream.of(values); // IntStream

    }
}
