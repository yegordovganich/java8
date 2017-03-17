package exercises.ch3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
18. Implement a version of the unchecked method in Section 3.8, “Dealing with
Exceptions,” on page 58, that generates a Function<T, U> from a lambda that
throws checked exceptions. Note that you will need to find or provide a
functional interface whose abstract method throws arbitrary exceptions.
 */
public class Ex18 {
    public static void main(String[] args) {
        unchecked((String str) -> (Files.readAllBytes(Paths.get(str)))).apply("resources/text.txt");
    }

    public static <T, U> Function<T, U> unchecked(FunctionWhichThrows<T, U> f) {
        return (x) -> {
            try {
                return f.apply(x);
            }
            catch (Exception e) {
                System.out.println("No such file");
                 throw new RuntimeException(e);
            }
            catch (Throwable t) {
                throw t;
            }
        };
    }

    @FunctionalInterface
    interface FunctionWhichThrows<T, U> {
        U apply(T t) throws Exception;
    }
}
