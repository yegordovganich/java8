package exercises.ch3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
7. Write a method that generates a Comparator<String> that can be normal or reversed,
case-sensitive or case-insensitive, space-sensitive or space-insensitive,
or any combination thereof. Your method should return a lambda expression.
 */
public class Ex7 {
    public static void main(String[] args) {
        String[] array = {"aaa", " bb", "CC"};

//        Arrays.sort(array, myComparator(EnumSet.noneOf(ComparatorModes.class)));
//        Arrays.asList(array).forEach(System.out::println); // " bb", "CC", "aaa"

//        Arrays.sort(array, myComparator(EnumSet.of(ComparatorModes.SPACE_INSENSITIVE)));
//        Arrays.asList(array).forEach(System.out::println); // "CC", "aaa", " bb"

//        Arrays.sort(array, myComparator(EnumSet.of(ComparatorModes.REVERSE)));
//        Arrays.asList(array).forEach(System.out::println); // "aaa", "CC", " bb"

        Arrays.sort(array, myComparator(EnumSet.of(ComparatorModes.REVERSE, ComparatorModes.CASE_INSENSITIVE)));
        Arrays.asList(array).forEach(System.out::println); // "CC", "aaa", " bb"
    }

    private static Comparator<String> myComparator(EnumSet<ComparatorModes> modes) {
        return (x, y) -> {
            if (modes.contains(ComparatorModes.CASE_INSENSITIVE)) {
                x = x.toLowerCase();
                y = y.toLowerCase();
            }
            if (modes.contains(ComparatorModes.SPACE_INSENSITIVE)) {
                x = x.replace(" ", "");
                y = y.replace(" ", "");
            }
            return modes.contains(ComparatorModes.REVERSE) ? y.compareTo(x) : x.compareTo(y);
        };
    }
}

enum ComparatorModes {
    REVERSE,
    CASE_INSENSITIVE,
    SPACE_INSENSITIVE
}
