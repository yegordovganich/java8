package exercises.ch6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by y.dovganich on 03.04.2017.
 */
/*
7. In a ConcurrentHashMap<String, Long>, find the key with maximum value (breaking
ties arbitrarily). Hint: reduceEntries.
 */
public class Ex7 {
    public static void main(String[] args) throws IOException {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        List<String> words = Arrays.asList(
                new String(Files.readAllBytes(Paths.get("src/resources/WarAndPeace.txt")), StandardCharsets.UTF_8)
                        .split("[\\P{L}]+"));
        words.parallelStream().forEach(str -> map.computeIfAbsent(str, s -> (long) s.length()));

        Map.Entry<String, Long> result = map.reduceEntries(10, (x, y) -> y.getValue() > x.getValue() ? y : x);
        System.out.println(result.getKey() + ": " + result.getValue());
    }
}
