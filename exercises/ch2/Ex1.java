package exercises.ch2;

import java.util.stream.Stream;

/**
 * Created by y.dovganich on 13.02.2017.
 */

/*
1. Write a parallel version of the for loop in Section 2.1, “From Iteration to
Stream Operations,” on page 22. Obtain the number of processors. Make that
many separate threads, each working on a segment of the list, and total up
the results as they come in.

int count = 0;
for (String w : words) {
 if (w.length() > 12) count++;
}
 */

public class Ex1 {
    public static void main(String[] args) {
        Stream<String> words = Stream.of("some", "short", "words", "very_very_very_long_word", "second_very_long_word");

//        long count = words.filter(w -> w.length() > 12).count();
//        System.out.println(count);

        // parallel
        long countP = words.parallel().filter(w -> w.length() > 12).count();
        System.out.println(countP);

    }
}
