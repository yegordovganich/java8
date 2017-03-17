package exercises.ch3;

import java.util.function.Function;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
23. Define a map operation for a class Pair<T> that represents a pair of objects of
type T.
 */
public class Ex23 {
    public static void main(String[] args) {
        Pair<String> pair = new Pair("Hello", "World");
        Pair<String> result = pair.map(x -> x + "!");
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

        <U> Pair<U> map(Function<T, U> fn) {
            return new Pair<U>(fn.apply(first), fn.apply(second));
        }
    }
}
