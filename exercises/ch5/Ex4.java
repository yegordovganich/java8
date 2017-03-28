package exercises.ch5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by y.dovganich on 27.03.2017.
 */
/*
4. Write an equivalent of the Unix cal program that displays a calendar for a
month. For example, java Cal 3 2013 should display
             1  2  3
 4  5  6  7  8  9 10
11 12 13 14 15 16 17
18 19 20 21 22 23 24
25 26 27 28 29 30 31
indicating that March 1 is a Friday. (Show the weekend at the end of
the week.)
 */
public class Ex4 {
    public static void main(String[] args) {
        printCalendar(2013, Month.MARCH);
        printCalendar(2017, Month.MARCH);
    }

    private static void printCalendar(int year, Month month) {
        LocalDate date = LocalDate.of(year, month, 1);
        String prefix = "";
        for (int i = date.getDayOfWeek().getValue(); i > 1; i--)
            prefix += "   ";
        System.out.print(prefix);

        while (date.getMonth() == month) {
            if (date.getDayOfMonth() < 10) System.out.print(" ");
            System.out.print(date.getDayOfMonth() + " ");
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) System.out.println();
            date = date.plusDays(1);
        }
        System.out.println();
    }
}
