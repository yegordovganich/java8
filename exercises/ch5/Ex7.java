package exercises.ch5;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by y.dovganich on 29.03.2017.
 */
/*
7. Implement a TimeInterval class that represents an interval of time, suitable for
calendar events (such as a meeting on a given date from 10:00 to 11:00).
Provide a method to check whether two intervals overlap.
 */
public class Ex7 {
    public static void main(String[] args) {
        TimeInterval t1 = new TimeInterval(LocalTime.of(10, 0), LocalTime.of(11, 0));
        assertFalse(t1.overlap(new TimeInterval(LocalTime.of(11, 5), LocalTime.of(11, 20))));
        assertTrue(t1.overlap(new TimeInterval(LocalTime.of(10, 30), LocalTime.of(11, 20))));
        assertTrue(t1.overlap(new TimeInterval(LocalTime.of(10, 30), LocalTime.of(10, 40))));
        assertTrue(t1.overlap(new TimeInterval(LocalTime.of(9, 0), LocalTime.of(10, 20))));
        assertFalse(t1.overlap(new TimeInterval(LocalTime.of(8, 0), LocalTime.of(9, 0))));
        assertTrue(t1.overlap(new TimeInterval(LocalTime.of(9, 0), LocalTime.of(12, 0))));
    }

    static class TimeInterval {
        private LocalTime start;
        private LocalTime end;

        private TimeInterval(LocalTime start, LocalTime end) {
            if (start == null || end == null || start.isAfter(end))
                throw new IllegalArgumentException();
            this.start = start;
            this.end = end;
        }

        private boolean overlap(TimeInterval that) {
            return !(this.end.isBefore(that.start) || this.start.isAfter(that.end));
        }
    }
}
