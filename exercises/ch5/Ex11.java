package exercises.ch5;

import java.time.*;

/**
 * Created by y.dovganich on 30.03.2017.
 */
/*
11. Your return flight leaves Frankfurt at 14:05 and arrives in Los Angeles at
16:40. How long is the flight? Write a program that can handle calculations
like this.
 */
public class Ex11 {
    public static void main(String[] args) {
        System.out.println(duration(
            LocalTime.of(14, 5),
            ZoneId.of("Europe/Berlin"),
            LocalTime.of(16, 40),
            ZoneId.of("America/Los_Angeles")));
    }

    private static Duration duration(LocalTime fromTime, ZoneId fromZoneId, LocalTime toTime, ZoneId toZoneId) {
        LocalDate today = LocalDate.now();
        ZonedDateTime from = ZonedDateTime.of(today, fromTime, fromZoneId);
        ZonedDateTime to = ZonedDateTime.of(
                fromTime.isAfter(toTime) || fromTime.equals(toTime) ?  today.plusDays(1) : today,
                toTime,
                toZoneId);
        return Duration.between(from, to);

    }
}
