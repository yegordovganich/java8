package exercises.ch1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by y.dovganich on 21.02.2017.
 */
/*
9. Form a subclass Collection2 from Collection and add a default method void
forEachIf(Consumer<T> action, Predicate<T> filter) that applies action to each
element for which filter returns true. How could you use it?
 */
public class Ex9 {
    public static void main(String[] args) {
        Collection2<String> c = new ArrayList2<>();
        c.add("word1");
        c.add("word2");
        c.add("word32");

        List<String> r = new ArrayList<>();
        c.forEachIf(
            x -> r.add(x.concat("!!!")),
            x -> x.endsWith("2")
        );
        r.forEach(System.out::println);
    }
}

interface Collection2<T> extends Collection<T> {
    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        this.forEach(e -> {
            if (filter.test(e)) action.accept(e);
        });
    }
}

class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {}
