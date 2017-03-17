package exercises.ch2;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 19.02.2017.
 */
/*
12. Count all short words in a parallel Stream<String>, as described in Section 2.13,
“Parallel Streams,” on page 40, by updating an array of AtomicInteger. Use
the atomic getAndIncrement method to safely increment each counter.
 */
public class Ex12 {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("some", "short", "words", "very_very_very_long_word", "second_very_long_word");
        AtomicInteger[] counter = new AtomicInteger[11];
        words.parallel().forEach(s -> {
            int l = s.length() - 1;
            if (l < 12) {
                AtomicInteger c = counter[l];
                if (c == null) counter[l] = new AtomicInteger(0);
                counter[l].getAndIncrement();
            }
        });
        // for (AtomicInteger)
        Arrays.asList(counter).forEach(System.out::println);
    }
}
