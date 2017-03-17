package exercises.ch2;

import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by y.dovganich on 19.02.2017.
 */
/*
13. Repeat the preceding exercise, but filter out the short strings and use the
collect method with Collectors.groupingBy and Collectors.counting.
 */
public class Ex13 {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("some", "short", "words", "very_very_very_long_word", "second_very_long_word");
        words
            .parallel()
            .filter(s -> s.length() < 12)
            .collect(groupingBy(String::length, counting()))
            .forEach((k, v) -> System.out.printf("%d : %d\n", k, v));
    }
}
