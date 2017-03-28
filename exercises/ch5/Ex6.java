package exercises.ch5;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by y.dovganich on 28.03.2017.
 */
/*
6. List all Friday the 13th in the twentieth century.
 */
public class Ex6 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(1900, 1, 13);
        LocalDate end = LocalDate.of(2000, 1, 1);
        while (date.isBefore(end)) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY)
                System.out.println(date);
            date = date.plusMonths(1);
        }
    }
}
