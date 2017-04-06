package exercises.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.PasswordAuthentication;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by y.dovganich on 06.04.2017.
 */
/*
11. Write a method
public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until)
that asynchronously repeats the action until it produces a value that is
accepted by the until function, which should also run asynchronously. Test
with a function that reads a java.net.PasswordAuthentication from the console,
and a function that simulates a validity check by sleeping for a second and
then checking that the password is "secret". Hint: Use recursion.
 */
public class Ex11 {
    public static void main(String[] args) {
        Supplier<PasswordAuthentication> action = () -> {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Password: ");
            String password = "";
            try {
                password = bufferedReader.readLine();
            } catch (IOException e) {
                System.out.println("Error when reading line.");
                e.printStackTrace();
            }
            return new PasswordAuthentication("user", password.toCharArray());
        };

        Predicate<PasswordAuthentication> until = (passwordAuthentication) -> {
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                System.out.println("Error when trying to sleep.");
                e.printStackTrace();
            }
            return String.valueOf(passwordAuthentication.getPassword()).equals("secret");
        };

        repeat(action, until).thenAccept(pa -> System.out.printf("Logged in: %s %s%n", pa.getUserName(), String.valueOf(pa.getPassword())));

        ForkJoinPool.commonPool().awaitQuiescence(1, TimeUnit.MINUTES);
    }

    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action).thenApplyAsync(data -> {
           if (until.test(data)) return data;
           else return repeat(action, until).join();
        });
    }
}
