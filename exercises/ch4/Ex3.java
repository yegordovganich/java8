package exercises.ch4;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by y.dovganich on 09.03.2017.
 */
/*
3. Consider a class with many JavaFX properties, most of which are never
changed from a default. Show how the property can be set up on demand,
when it is set to a nondefault value or when the xxxProperty() method is called
for the first time
 */
public class Ex3 {
    @Test
    public static void main(String[] args) {
        MyClass mc1 = new MyClass();
        assertNull(mc1.property1);
        mc1.setProperty1(25);
        assertNull(mc1.property1);
        mc1.setProperty1(26);
        assertNotNull(mc1.property1);

        MyClass mc2 = new MyClass();
        assertNull(mc2.property1);
        mc1.p1Property();
        assertNotNull(mc1.property1);
    }

    private static class MyClass {
        private final int DEFAULT_VALUE = 25;
        private int value1 = DEFAULT_VALUE;
        IntegerProperty property1;

        void setProperty1(int value) {
            if (this.property1 != null) {
                this.property1.setValue(value);

            }else if (value != DEFAULT_VALUE) {
                property1 = new SimpleIntegerProperty(value);
            }
        }

        IntegerProperty p1Property() {
            if (property1 == null) property1 = new SimpleIntegerProperty(value1);
            return property1;
        }

        int getProperty1() {
            return property1 != null ? property1.get() : value1;
        }
    }
}
