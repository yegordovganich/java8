package excercises.ch1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by y.dovganich on 21.02.2017.
 */
/*
4. Given an array of File objects, sort it so that the directories come before the
files, and within each group, elements are sorted by path name. Use a lambda
expression, not a Comparator
 */
public class Ex4 {
    public static void main(String[] args) {
        File dir = new File("c:/repo/perform");
        File[] arr = dir.listFiles();
        Arrays.sort(arr, (x, y) -> {
            if (x.isDirectory() && !y.isDirectory()) {
                return -1;
            } else if (!x.isDirectory() && y.isDirectory()) {
                return 1;
            } else {
                return x.getName().toLowerCase().compareTo(y.getName().toLowerCase());
            }
        });
        Arrays.asList(arr).forEach(System.out::println);
    }
}
