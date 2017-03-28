package exercises.ch5;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * Created by y.dovganich on 27.03.2017.
 */
/*
1. Compute Programmer’s Day without using plusDays.
 */
public class Ex1 {
    public static void main(String[] args) {
        Period period = Period.ofDays(255);
        LocalDate pDay = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1).plus(period);
        System.out.println(pDay);
    }
}
