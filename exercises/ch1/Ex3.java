package exercises.ch1;

import java.io.File;
import java.util.Arrays;

/**
 * Created by y.dovganich on 20.02.2017.
 */
/*
3. Using the list(FilenameFilter) method of the java.io.File class, write a method
that returns all files in a given directory with a given extension. Use a lambda
expression, not a FilenameFilter. Which variables from the enclosing scope does
it capture?
 */
public class Ex3 {
    public static void main(String[] args) {
        Arrays.asList(allFilesExtension("c:/repo/perform", "bat")).forEach(System.out::println);
    }

    static String[] allFilesExtension(String dirPath, String ext) {
        File dir = new File(dirPath);
        return dir.list((__, name) -> name.endsWith(ext));
    }
}
