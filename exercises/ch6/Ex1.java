package exercises.ch6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * Created by y.dovganich on 31.03.2017.
 */
/*
1. Write a program that keeps track of the longest string that is observed by a
number of threads. Use an AtomicReference and an appropriate accumulator.
 */
public class Ex1 {
    public static void main(String[] args) {
        // AtomicReference<String> longest = new AtomicReference<>("");
        AtomicReference<String> longest = new AtomicReference<>();
        LongAccumulator accumulator = new LongAccumulator(Long::max, 0);

        String contents = null;
        try {
            contents = new String(Files.readAllBytes(Paths.get("src/resources/WarAndPeace.txt")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        // words.parallelStream().forEach(str -> longest.updateAndGet(s -> str.length() > s.length() ? str : s));

        words.parallelStream().forEach(str -> longest.updateAndGet(s -> {
            String result = str.length() > accumulator.intValue() ? str : s;
            accumulator.accumulate(result.length());
            return result;
        }));

        System.out.println(longest.get());

    }
}
