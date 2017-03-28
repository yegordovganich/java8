package exercises.ch5;

import java.time.LocalDate;

/**
 * Created by y.dovganich on 27.03.2017.
 */
/*
2. What happens when you add one year to LocalDate.of(2000, 2, 29)? Four years?
Four times one year?
 */
public class Ex2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2000, 2, 29);
        System.out.println(date.plusYears(1)); // 2001-02-28
        System.out.println(date.plusYears(4)); // 2004-02-29
        System.out.println(date.plusYears(1).plusYears(1).plusYears(1).plusYears(1)); // 2004-02-28
    }
}
