package excercises.ch3;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
17. Implement a doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable>)
method that executes first and second in parallel, calling the handler if
either method throws an exception.
 */
public class Ex17 {
    public static void main(String[] args) {
        doInParallelAsync(
                () -> {
                    if (System.currentTimeMillis() % 2 == 0) System.out.println("Hello");
                    else throw new RuntimeException("EXCEPTION1");
                },
                () -> {
                    if (System.currentTimeMillis() % 3 == 0) System.out.println("World");
                    else throw new RuntimeException("EXCEPTION2");
                },
                (t) -> t.printStackTrace()
        );
    }

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        Thread thread = new Thread(
            () -> {
                new Thread(
                    () -> {
                        try {
                            first.run();
                        } catch (Throwable t) {
                            handler.accept(t);
                        }
                    }
                ).start();
                new Thread(
                    () -> {
                        try {
                            second.run();
                        } catch (Throwable t) {
                            handler.accept(t);
                        }
                    }
                ).start();
            }
        );
        thread.start();
    }

}
