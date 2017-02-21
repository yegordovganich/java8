package excercises.ch1;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by y.dovganich on 20.02.2017.
 */
/*
1. Is the comparator code in the Arrays.sort method called in the same thread as
the call to sort or a different thread?
 */
public class Ex1 {
    public static void main(String[] args) {
        final long currentThreadId = Thread.currentThread().getId();
        System.out.println("currentThreadId: " + currentThreadId);
        String[] words = {"some", "new", "word"};

        //sort
        CopyOnWriteArraySet<Long> threadIds = new CopyOnWriteArraySet<>();
        Arrays.sort(words, (x, y) -> {
            threadIds.add(Thread.currentThread().getId());
            return x.compareTo(y);
        });
        System.out.println("size: " + threadIds.size());
        System.out.println("id: " + threadIds.toArray()[0]);

        //parallelSort
        threadIds.clear();
        Arrays.parallelSort(words, (x, y) -> {
            threadIds.add(Thread.currentThread().getId());
            return x.compareTo(y);
        });
        System.out.println("parallel size:" + threadIds.size());
        System.out.println("parallel id: " + threadIds.toArray()[0]);
    }
}
