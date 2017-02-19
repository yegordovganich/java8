package excercises.ch2;

import java.util.stream.Stream;

/**
 * Created by y.dovganich on 13.02.2017.
 */

/*
2. Verify that asking for the first five long words does not call the filter method
once the fifth long word has been found. Simply log each method call.
 */

public class Ex2 {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("some", "short", "words", "very_very_very_long_word", "second_very_long_word");

        System.out.println("P0");
        Stream<String> filteredWords = words.parallel().filter(w -> w.length() > 12).peek(System.out::println);
        System.out.println("P1");
        long count = filteredWords.count();
        System.out.println("P2");
    }
}
