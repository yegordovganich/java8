package exercises.ch2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by y.dovganich on 14.02.2017.
 */

/*
8. Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
that alternates elements from the streams first and second, stopping when
one of them runs out of elements.
 */
public class Ex8 {
    public static void main(String[] args) {
        String[] s1 = {"1", "2"};
        String[] s2 = {"a", "b", "c", "d"};

        zip(Arrays.stream(s1), Arrays.stream(s2)).forEach(System.out::println);
        System.out.println("--------------");
        zip(Arrays.stream(s2), Arrays.stream(s1)).forEach(System.out::println);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        Queue<T> secondList = second.collect(Collectors.toCollection(LinkedList::new));
        List<T> result = new LinkedList<>();
        for (T f : firstList) {
            result.add(f);
            if (secondList.isEmpty()) {
                break;
            } else {
                result.add(secondList.poll());
            }
        }
        return result.stream();
    }
}
