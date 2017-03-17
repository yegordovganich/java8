package exercises.ch3;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by y.dovganich on 01.03.2017.
 */
/*
1. Enhance the lazy logging technique by providing conditional logging. A
typical call would be logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]).
Don’t evaluate the condition if the logger won’t log the message.
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] array = {11, 12, 13, 14, 15, 16, 17};
        Logger.getGlobal().setLevel(Level.ALL);
        for (int i = 0; i < 5; i++)
            log(i, array);
    }

    private static void log(int i, int[] a) {
        logIf(Level.INFO, () -> i > 2, () -> "a[" + i + "] = " + a[i]);
    }

    private static void logIf(Level level, BooleanSupplier condition, Supplier<String> message) {
        Logger logger = Logger.getGlobal();
        if (logger.isLoggable(level) && condition.getAsBoolean())
           logger.log(level, message.get());
    }
}
