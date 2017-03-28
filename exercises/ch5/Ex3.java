package exercises.ch5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

/**
 * Created by y.dovganich on 27.03.2017.
 */
/*
3. Implement a method next that takes a Predicate<LocalDate> and returns an
adjuster yielding the next date fulfilling the predicate. For example,
today.with(next(w -> getDayOfWeek().getValue() < 6))
computes the next workday.
 */
public class Ex3 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.with(next(w -> w.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) < 0)));
    }

    private static TemporalAdjuster next(Predicate<LocalDate> predicate) {
        return TemporalAdjusters.ofDateAdjuster(d -> {
            LocalDate temp = d;
            do {
                temp = temp.plusDays(1);
            } while (!predicate.test(temp));
            return temp;
        });
    }
}
