package exercises.ch5;

import java.time.*;

/**
 * Created by y.dovganich on 30.03.2017.
 */
/*
12. Write a program that solves the problem described at the beginning of
Section 5.5, “Zoned Time,” on page 109. Read a set of appointments in different
time zones and alert the user which ones are due within the next hour in
local time.
 */
public class Ex12 {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        ZoneId localZoneId = ZoneId.systemDefault();

        alert(LocalTime.of(11, 0), ZoneId.of("Europe/Paris"), localTime, localZoneId);
        alert(LocalTime.of(12, 0), ZoneId.of("Europe/Paris"), localTime, localZoneId);
        alert(LocalTime.of(13, 0), ZoneId.of("Europe/Paris"), localTime, localZoneId);
    }

    private static void alert(LocalTime appTime, ZoneId appZoneId, LocalTime localTime, ZoneId localZoneId) {
        LocalDate today = LocalDate.now();
        ZonedDateTime appointment = ZonedDateTime.of(today, appTime, appZoneId);
        long duration = Duration.between(
                appointment.withZoneSameInstant(localZoneId),
                ZonedDateTime.of(today, localTime, localZoneId))
            .toMinutes();
        if (duration > 0 && duration < 60) System.out.println(appointment);
    }
}
