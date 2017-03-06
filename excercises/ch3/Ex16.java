package excercises.ch3;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
16. Implement the doInOrderAsync of Section 3.8, “Dealing with Exceptions,” on
page 58, where the second parameter is a BiConsumer<T, Throwable>. Provide
a plausible use case. Do you still need the third parameter?
 */
public class Ex16 {
    public static void main(String[] args) {
        doInOrderAsync(
            () -> "Hello",
            (str, e) -> {
                if (str != null) {
                    System.out.println(str);
                } else {
                    e.printStackTrace();
                }
            }
        );
    }

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result, null);
                } catch (Throwable t) {
                    second.accept(null, t);
                }
            }
        };
        t.start();
    }
}
