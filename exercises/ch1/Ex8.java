package exercises.ch1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y.dovganich on 21.02.2017.
 */
/*
8. What happens when a lambda expression captures values in an enhanced
for loop such as this one?
String[] names = { "Peter", "Paul", "Mary" };
List<Runnable> runners = new ArrayList<>();
for (String name : names)
 runners.add(() -> System.out.println(name));
Is it legal? Does each lambda expression capture a different value, or do they
all get the last value? What happens if you use a traditional loop for (int i = 0;
i < names.length; i++)?
 */
public class Ex8 {
    public static void main(String[] args) {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));
        runners.forEach(x -> new Thread(x).start());

        runners.clear();
        for (int i =0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> System.out.println(name));
        }
        // runners.forEach(Runnable::run);
        runners.forEach(x -> new Thread(x).start());
    }
}

/*
Peter
Mary
Paul

Peter
Paul
Mary

 */
