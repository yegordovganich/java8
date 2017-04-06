package exercises.ch6;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by y.dovganich on 03.04.2017.
 */
/*
5. Write an application in which multiple threads read all words from a collection
of files. Use a ConcurrentHashMap<String, Set<File>> to track in which files
each word occurs. Use the merge method to update the map.
 */
public class Ex5 {
    public static void main(String[] args) {
        Ex5 thisClass = new Ex5();
        File[] files = {
                new File(thisClass.getClass().getResource("/resources/WarAndPeace.txt").getFile()),
                new File(thisClass.getClass().getResource("/resources/text.txt").getFile()),
        };

        ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

        Arrays.asList(files).parallelStream().forEach(f -> {
            try {
                List<String> words = Arrays.asList(
                        new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8)
                                .split("[\\P{L}]+"));
                words.parallelStream()
                        .forEach(str -> map
                                .merge(str, new HashSet<>(), (existingValue, initValue) -> existingValue)
                                .add(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println(map.get("at"));
        assertEquals(2, map.get("at").size());
        assertEquals(1, map.get("enthusiasm").size());
    }
}
