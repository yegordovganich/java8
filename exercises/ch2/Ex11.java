package exercises.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;

/**
 * Created by y.dovganich on 15.02.2017.
 */
 /*
 11. It should be possible to concurrently collect stream results in a single ArrayList,
instead of merging multiple array lists, provided it has been constructed with
the stream’s size, since concurrent set operations at disjoint positions
are threadsafe. How can you achieve that?
  */
public class Ex11 {
    public static void main(String[] args) {
        List<ArrayList<String>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList("01", "02", "03")));
        list.add(new ArrayList<>(Arrays.asList("04", "05")));
        list.add(new ArrayList<>(Arrays.asList("06", "07", "08", "09", "10")));

        List<String> stringList = collect(list.stream());
        System.out.println("Size: " + stringList.size());
        stringList.forEach(System.out::println);
    }


    static List<String> collect(Stream<ArrayList<String>> stream) {
        String[] array = stream.flatMap(Collection::stream).toArray(String[]::new);
        IntStream range = IntStream.range(0, array.length);
        List<String> result = new ArrayList<>(Arrays.asList(new String[array.length]));
        range.parallel().forEach(e -> result.set(e, array[e]));
        return result;
    }
}
