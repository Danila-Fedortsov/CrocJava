package ru.croc.javaschool.homework4.task1.employment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Тестирование класса {@link EmployeeManagement}.
 *
 * @author Danila Fedortsov
 */
public class EmployeeManagementTest {
    /**
     * Тестирование метода {@link EmployeeManagement#getCompanies}. <br>
     * В списке присутствуют 4 человека - 2 организации по 2 человека (самый главный и его подчинённый).
     */
    @Test
    public void getCompaniesTest() {
        ArrayList<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Alex"),
                new Employee(2, "Morgan"),
                new Employee(3, "Megan"),
                new Employee(4, "Alice")
        ));
        employees.get(0).setManager(null);
        employees.get(1).setManager(employees.get(0));
        employees.get(2).setManager(employees.get(3));
        employees.get(3).setManager(null);

        ArrayList<ArrayList<Employee>> companies = EmployeeManagement.getCompanies(employees);
        Assertions.assertNotNull(companies);
        Assertions.assertEquals(2, companies.size());
        Assertions.assertEquals(2, companies.get(0).size());
        Assertions.assertEquals(2, companies.get(1).size());
        Assertions.assertTrue(companies.get(0).contains(employees.get(0)));
        Assertions.assertTrue(companies.get(0).contains(employees.get(1)));
        Assertions.assertTrue(companies.get(1).contains(employees.get(2)));
        Assertions.assertTrue(companies.get(1).contains(employees.get(3)));
    }

    /**
     * Тестирование метода {@link EmployeeManagement#getManagersWithNumOfSub}. <br>
     * В списке присутствуют 5 человек - 1 организация. Chuck и Alice подчиняются Alex'у , а Alex и Megan в свою
     * очередь самому главному - Almond'у.
     */
    @Test
    public void getManagersWithNumOfSubTest() {
        String expectedStr = "{1#Almond=6, 2#Alex=2}";
        ArrayList<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Almond"),
                new Employee(2, "Alex"),
                new Employee(3, "Megan"),
                new Employee(4, "Alice"),
                new Employee(5, "Chuck")
        ));
        employees.get(0).setManager(null);
        employees.get(1).setManager(employees.get(0));
        employees.get(2).setManager(employees.get(0));
        employees.get(3).setManager(employees.get(1));
        employees.get(4).setManager(employees.get(1));

        Map<Employee, Integer> actualRes = EmployeeManagement.getManagersWithNumOfSub(employees);

        Assertions.assertNotNull(actualRes);
        Assertions.assertEquals(expectedStr, actualRes.toString());
    }

    /**
     * Тестирование метода {@link EmployeeManagement#isCorrectEmployeeList}. <br>
     * Проверяется то, как метод отлавливает передаваемые null вместо списка или объекта спика.
     */
    @Test
    public void isCorrectEmployeeListTestWithNull() {
        ArrayList<Employee> employees1 = null;
        ArrayList<Employee> employees2 = new ArrayList<>(List.of(
                new Employee(1, "Almond"),
                new Employee(2, "Alex"),
                new Employee(3, "Megan"),
                new Employee(4, "Alice"),
                new Employee(5, "Chuck")
        ));
        employees2.add(null);

        Assertions.assertFalse(EmployeeManagement.isCorrectEmployeeList(employees1));
        Assertions.assertFalse(EmployeeManagement.isCorrectEmployeeList(employees2));
    }

    /**
     * Тестирование метода {@link EmployeeManagement#isCorrectEmployeeList}. <br>
     * Тестируется факт определения методом колец в ориентированном графе сотрудников.
     */
    @Test
    public void isCorrectEmployeeListWithRingTest() {
        ArrayList<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Almond"),
                new Employee(2, "Alex")
        ));
        employees.get(0).setManager(employees.get(1));
        employees.get(1).setManager(employees.get(0));

        Assertions.assertFalse(EmployeeManagement.isCorrectEmployeeList(employees));
    }

    /**
     * Тестирование метода {@link EmployeeManagement#isCorrectEmployeeList}. <br>
     * Тест работы с корректным списком сотрудников.
     */
    @Test
    public void isCorrectEmployeeListWithCorrectListTest() {
        ArrayList<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Almond"),
                new Employee(2, "Alex"),
                new Employee(3, "Megan"),
                new Employee(4, "Alice"),
                new Employee(5, "Chuck")
        ));
        employees.get(0).setManager(null);
        employees.get(1).setManager(employees.get(0));
        employees.get(2).setManager(employees.get(0));
        employees.get(3).setManager(employees.get(1));
        employees.get(4).setManager(employees.get(1));

        Assertions.assertTrue(EmployeeManagement.isCorrectEmployeeList(employees));
    }
}