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
2. Consider a class with many JavaFX properties, such as a chart or table.
Chances are that in a particular application, most properties never have listeners
attached to them. It is therefore wasteful to have a property object per
property. Show how the property can be set up on demand, first using a
regular field for storing the property value, and then using a property object
only when the xxxProperty() method is called for the first time.
 */
public class Ex2 {
    @Test
    public static void main(String[] args) {
        MyClass mc1 = new MyClass(1);
        assertNull(mc1.property1);
        mc1.setProperty1(2);
        assertNull(mc1.property1);

        MyClass mc2 = new MyClass(7);
        mc1.p1Property().bind(mc2.p1Property());
        assertNotNull(mc1.property1);
        assertNotNull(mc2.property1);
        mc2.setProperty1(35);
        assertEquals(mc1.getProperty1(), mc2.getProperty1());
    }

    private static class MyClass {
        private int value1;
        IntegerProperty property1;

        MyClass(int value1) {
            this.value1 = value1;
        }
        void setProperty1(int value) {
            if (this.property1 == null) value1 = value;
            else this.property1.setValue(value);
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
