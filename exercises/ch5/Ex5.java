package exercises.ch5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

/**
 * Created by y.dovganich on 28.03.2017.
 */
/*
5. Write a program that prints how many days you have been alive.
 */
public class Ex5 {
    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(1987, Month.DECEMBER, 8);
        System.out.println(birth.until(LocalDate.now(), ChronoUnit.DAYS));
    }
}

