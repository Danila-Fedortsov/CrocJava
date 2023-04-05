package ru.croc.javaschool.task1.employment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тестирование класса {@link Employee}.
 *
 * @author Danila Fedortsov
 */
public class EmployeeTest {
    /**
     * Тестирование метода {@link Employee#getId}.
     */
    @Test
    public void getIdTest() {
        Integer expectedId = 22;
        Employee employee = new Employee(expectedId, "Max");

        Assertions.assertEquals(expectedId, employee.getId());
    }

    /**
     * Тестирование метода {@link Employee#getName}.
     */
    @Test
    public void getNameTest() {
        String expectedName = "Max";
        Employee employee = new Employee(22, expectedName);

        Assertions.assertEquals(expectedName, employee.getName());
    }

    /**
     * Тестирование метода {@link Employee#getManager}.
     */
    @Test
    public void getSetManagerTest() {
        Employee employee = new Employee(22, "Morty");
        Employee expectedEmployee = new Employee(38, "Rick");
        employee.setManager(expectedEmployee);

        Assertions.assertEquals(expectedEmployee, employee.getManager());
    }

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
