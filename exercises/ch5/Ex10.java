package exercises.ch5;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by y.dovganich on 30.03.2017.
 */
/*
10. Your flight from Los Angeles to Frankfurt leaves at 3:05 pm local time and
takes 10 hours and 50 minutes. When does it arrive? Write a program that
can handle calculations like this.
 */
public class Ex10 {
    public static void main(String[] args) {
        LocalDateTime departureDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 5));
        ZoneId departureZoneId = ZoneId.of("America/Los_Angeles");
        Duration duration = Duration.ofMinutes(650);
        ZoneId arrivalZoneId = ZoneId.of("Europe/Berlin");

        System.out.println(arrival(departureDateTime, departureZoneId, duration, arrivalZoneId));
    }

    private static ZonedDateTime arrival(
            LocalDateTime departureDateTime,
            ZoneId departureZoneId,
            Duration duration,
            ZoneId arrivalZoneId) {
        return ZonedDateTime.of(departureDateTime, departureZoneId)
                .plus(duration)
                .withZoneSameInstant(arrivalZoneId);
    }
}
