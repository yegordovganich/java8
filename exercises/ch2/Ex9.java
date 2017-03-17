package exercises.ch2;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 14.02.2017.
 */
/*
9. Join all elements in a Stream<ArrayList<T>> to one ArrayList<T>. Show how to do
this with the three forms of reduce.
 */
public class Ex9 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4);
        List<Integer> list3 = Arrays.asList(7, 8);

        joinLists1(list1, list2, list3);
        joinLists2(list1, list2, list3);
        joinLists3(list1, list2, list3);
        joinLists4(list1, list2, list3);
    }

    static <T> void joinLists1(List<T> ... lists) {
        Stream<List<T>> stream = Arrays.stream(lists);
        Optional<List<T>> result1 = stream.reduce((l1, l2) -> {
            List<T> res = new ArrayList<>(l1.size() + l2.size());
            res.addAll(l1);
            res.addAll(l2);
            return res;
        });

        result1.ifPresent(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }
    static <T> void joinLists2(List<T> ... lists) {
        Stream<List<T>> stream = Arrays.stream(lists);
        stream
            .reduce(new ArrayList<>(), (acc, e) -> {
                acc.addAll(e);
                return acc;
            })
            .forEach(System.out::print);
        System.out.println();
    }
    static <T> void joinLists3(List<T> ... lists) {
        Stream<List<T>> stream = Arrays.stream(lists);
        stream
            .reduce(new ArrayList<>(),
                (acc, e) -> {
                    acc.addAll(e);
                    return acc;
                },
                (x, y) -> {
                    x.addAll(y);
                    return x;
                }
            )
            .forEach(System.out::print);
        System.out.println();
    }
    static <T> void joinLists4(List<T> ... lists) {
        Stream<ArrayList<T>> stream = Arrays.stream(lists).map(ArrayList::new);

        // BiFunction<ArrayList<T>, ArrayList<T>, ArrayList<T>> bi = (x, y) -> { x.addAll(y); return x; };
        BinaryOperator<ArrayList<T>> bi = (x, y) -> { x.addAll(y); return x; };

        stream
                .reduce(new ArrayList<>(),bi, bi)
                .forEach(System.out::print);
        System.out.println();
    }
}
