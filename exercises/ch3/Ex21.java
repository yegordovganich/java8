package exercises.ch3;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
21. Supply a static method <T, U> Future<U> map(Future<T>, Function<T, U>). Return an
object of an anonymous class that implements all methods of the Future
interface. In the get methods, invoke the function.
 */
public class Ex21 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> str = executorService.submit(() -> "Hello");
        Future<String> result = map(str, x -> x + " World!");
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T, U> Future<U> map(Future<T> future, Function<T, U> fn) {
        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return fn.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return fn.apply(future.get(timeout, unit));
            }
        };
    }
}
