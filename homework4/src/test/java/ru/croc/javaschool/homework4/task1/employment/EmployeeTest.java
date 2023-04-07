package ru.croc.javaschool.homework4.task1.employment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тестирование класса {@link Employee}.
 *
 * @author Danila Fedortsov
 */
public class EmployeeTest {
    /**
     * Тестирование метода {@link Employee#toString()}.
     */
    @Test
    public void toStringTest() {
        Employee employee = new Employee(38, "Rick");
        String expectedString = employee.getId() + "#" + employee.getName();

        Assertions.assertEquals(expectedString, employee.toString());
    }

    /**
     * Тестирование метода {@link Employee#compareTo}.
     */
    @Test
    public void compareToTest() {
        Employee employee1 = new Employee(22, "Morty");
        Employee employee2 = new Employee(38, "Rick");
        Employee employee3 = new Employee(39, "Rick");

        Assertions.assertTrue(employee2.compareTo(employee1) > 0);
        Assertions.assertTrue(employee2.compareTo(employee3) < 0);
    }
}
