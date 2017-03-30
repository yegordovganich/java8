package exercises.ch5;

import java.time.Instant;
import java.time.ZoneId;

/**
 * Created by y.dovganich on 30.03.2017.
 */
/*
9. Again using stream operations, find all time zones whose offsets aren’t full
hours.
 */
public class Ex9 {
    public static void main(String[] args) {
        Instant now = Instant.now();
        ZoneId.getAvailableZoneIds().stream()
                .map(id -> now.atZone(ZoneId.of(id)).getOffset())
                .filter(zo -> zo.getTotalSeconds() % 3600 != 0)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
