package exercises.ch1;

/**
 * Created by y.dovganich on 21.02.2017.
 */
/*
7. Write a static method andThen that takes as parameters two Runnable instances
and returns a Runnable that runs the first, then the second. In the main method,
pass two lambda expressions into a call to andThen, and run the returned
instance.
 */
public class Ex7 {
    public static void main(String[] args) {
        new Thread (andThen(
            () -> System.out.println("1"),
            () -> System.out.println("2")
        )).start();
    }

    static Runnable andThen(Runnable t1, Runnable t2) {
        return () -> {
            t1.run();
            t2.run();
        };
    }
}
