package excercises.ch3;

import java.util.function.BooleanSupplier;

/**
 * Created by y.dovganich on 01.03.2017.
 */
/*
3. Java 1.4 added assertions to the language, with an assert keyword. Why were
assertions not supplied as a library feature? Could they be implemented as
a library feature in Java 8?
 */
public class Ex3 {
    public static void main(String[] args) {
        assertJ8(() -> true);
        assertJ8(() -> 1 == 2);
    }

    private static void assertJ8(BooleanSupplier assertion) {
        if (!assertion.getAsBoolean()) throw new AssertionError();
    }
}
