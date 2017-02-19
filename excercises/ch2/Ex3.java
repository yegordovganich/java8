package excercises.ch2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 13.02.2017.
 */

/*
3. Measure the difference when counting long words with a parallelStream instead
of a stream. Call System.currentTimeMillis before and after the call, and print the
difference. Switch to a larger document (such as War and Peace) if you have
a fast computer.
 */

public class Ex3 {
    public static void main(String[] args) throws IOException {
        Stream<String> words1 = Stream.of("some", "short", "words", "very_very_very_long_word", "second_very_long_word");
        Stream<String> words2 = Stream.of("some", "short", "words", "very_very_very_long_word", "second_very_long_word");

        // 38 ms
        long t1 = System.currentTimeMillis();
        long count = words1.filter(w -> w.length() > 12).count();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        // 62 ms
        t1 = System.currentTimeMillis();
        long countP = words2.parallel().filter(w -> w.length() > 12).count();
        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        String contents = new String(Files.readAllBytes(Paths.get("resources/WarAndPeace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        // 58 ms
        t1 = System.currentTimeMillis();
        long countBig = words.stream().filter(w -> w.length() > 12).count();
        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        // 55 ms
        t1 = System.currentTimeMillis();
        long countBigP = words.parallelStream().filter(w -> w.length() > 12).count();
        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

    }
}
