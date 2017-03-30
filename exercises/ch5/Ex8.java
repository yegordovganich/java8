package exercises.ch5;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by y.dovganich on 29.03.2017.
 */
/*
8. Obtain the offsets of today’s date in all supported time zones for the current
time instant, turning ZoneId.getAvailableIds into a stream and using stream
operations.
 */
public class Ex8 {
    public static void main(String[] args) {
        Instant now = Instant.now();
        ZoneId.getAvailableZoneIds().stream()
                .map(id -> now.atZone(ZoneId.of(id)).getOffset())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        ZoneId.getAvailableZoneIds().stream()
                .collect(Collectors.toMap(
                        id -> now.atZone(ZoneId.of(id)).getOffset(),
                        Function.identity(),
                        (existingValue, newValue) -> existingValue,
                        TreeMap::new))
                .forEach((k, v) -> System.out.println(k + " " + v));


    }
}
