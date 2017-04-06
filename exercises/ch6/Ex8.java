package exercises.ch6;

import java.util.Arrays;

/**
 * Created by y.dovganich on 04.04.2017.
 */
/*
8. How large does an array have to be for Arrays.parallelSort to be faster than
Arrays.sort on your computer?
 */
public class Ex8 {
    public static void main(String[] args) {
        double[] array = new double [10_000];
        Arrays.parallelSetAll(array, __ -> Math.random());

        long start;

        start = System.currentTimeMillis();
        Arrays.sort(array);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        Arrays.parallelSort(array);
        System.out.println(System.currentTimeMillis() - start);

        /*
            10_000: 0 / 10, 4 / 2, 0 / 0
            100_000: 40 / 0
            1_000_000: 130 / 36
            10_000_000: 1032 / 192
         */
    }
}
