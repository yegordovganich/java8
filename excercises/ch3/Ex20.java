package excercises.ch3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by y.dovganich on 06.03.2017.
 */
/*
20. Supply a static method <T, U> List<U> map(List<T>, Function<T, U>).
 */
public class Ex20 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        map(list, x -> x + 1).forEach(System.out::println);
    }
    private static <T, U> List<U> map(List<T> list, Function<T, U> fn) {
        return list.parallelStream().map(fn).collect(Collectors.toList());
    }
}
