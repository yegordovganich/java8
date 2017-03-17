package exercises.ch1;

import java.io.File;
import java.util.Arrays;

/**
 * Created by y.dovganich on 20.02.2017.
 */
/*
2. Using the listFiles(FileFilter) and isDirectory methods of the java.io.File class,
write a method that returns all subdirectories of a given directory. Use a
lambda expression instead of a FileFilter object. Repeat with a method
expression.
 */
public class Ex2 {
    public static void main(String[] args) {
        File dir = new File(".");

        File[] subdirs1 = dir.listFiles(x -> x.isDirectory());
        Arrays.asList(subdirs1).forEach(System.out::println);
        File[] subdirs2 = dir.listFiles(File::isDirectory);
        Arrays.asList(subdirs2).forEach(System.out::println);

    }
}
