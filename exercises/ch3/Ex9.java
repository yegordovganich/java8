package exercises.ch3;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
9. Write a method lexicographicComparator(String... fieldNames) that yields a comparator
that compares the given fields in the given order. For example, a
lexicographicComparator("lastname", "firstname") takes two objects and, using
reflection, gets the values of the lastname field. If they are different, return the
difference, otherwise move on to the firstname field. If all fields match, return 0.
 */
public class Ex9 {
    static class Person {
        private String firstName;
        private String lastName;
        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person("Jim", "Smith"),
                new Person("Zalex", "Lee"),
                new Person("Jim", "Lee")
        };
        Arrays.sort(people, lexicographicComparator("lastName", "firstName"));
        Arrays.asList(people).forEach(System.out::println);
    }

    private static <T> Comparator<T> lexicographicComparator(String... fields) {
        return (x, y) -> {
            for (String field : fields) {
                try {
                    Field f = x.getClass().getDeclaredField(field);
                    f.setAccessible(true);
                    int result = f.get(x).toString().compareTo(f.get(y).toString());
                    if (result != 0) return result;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        };
    }
}
