package exercises.ch3;

import java.util.function.BiFunction;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
24. Can you define a flatMap method for Pair<T>? If so, what is it? If not, why not?
 */
public class Ex24 {
    public static void main(String[] args) {
        Pair<String> pair = new Pair("Hello", "World");
        Pair<String> result = pair.flatMap((x, y) -> new Pair(x + "!", y + "!"));
        System.out.println(result.first);
        System.out.println(result.second);
    }

    private static class Pair<T> {
        T first;
        T second;

        private Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        <U> Pair<U> flatMap(BiFunction<T, T, Pair<U>> fn) {
            return fn.apply(first, second);
        }
    }
}
