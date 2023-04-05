package ru.croc.javaschool.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.croc.javaschool.task1.employment.Employee;
import ru.croc.javaschool.task1.employment.EmployeeManagement;

/**
 * Пример сценария работы с классом {@link EmployeeManagement}.
 *
 * @author Danila Fedortsov.
 */
public class SampleScript {
    /**
     * Инкремент для назначения уникального номера сотруднику.
     */
    public static int increment = 0;

    /**
     * Точка входа в программу для task1.
     *
     * @param args args
     */
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>(List.of(
                new Employee(inc(), "Alice"),
                new Employee(inc(), "Bob"),
                new Employee(inc(), "Nigel"),
                new Employee(inc(), "Sam"),
                new Employee(inc(), "Alice"),
                new Employee(inc(), "Sarah"),
                new Employee(inc(), "Daniel"),
                new Employee(inc(), "Stan")
        ));
        employees.get(0).setManager(null);             // 1#Alice главная.
        employees.get(1).setManager(employees.get(0)); // Bob     подчиняется 1#Alice.
        employees.get(2).setManager(employees.get(0)); // Nigel   подчиняется 1#Alice.
        employees.get(3).setManager(employees.get(1)); // Sam     подчиняется Bob.
        employees.get(4).setManager(employees.get(3)); // 5#Alice подчиняется Sam.
        employees.get(5).setManager(null);             // Sarah   главная.
        employees.get(6).setManager(employees.get(5)); // Daniel  подчиняется Sarah.
        employees.get(7).setManager(employees.get(4)); // Stan    подчиняется 5#Alice

        if (EmployeeManagement.isCorrectEmployeeList(employees)) {
            System.out.println("Распределение по компаниям:\n");
            ArrayList<ArrayList<Employee>> companies = EmployeeManagement.getCompanies(employees);
            for (int i = 0; i < companies.size(); i++) {
                System.out.printf("Компания %s:%n", i + 1);
                for (Employee e : companies.get(i)) {
                    System.out.println(e);
                }
                System.out.println();
            }

            System.out.println("\nКоличество сотрудников в подчинении у каждого менеджера:\n");
            HashMap<Employee, Integer> managers = EmployeeManagement.getManagersWithNumOfSub(employees);
            for (Employee e : managers.keySet()) {
                System.out.printf("У %-7s %s подчинённых%n", e, managers.get(e));
            }
        } else {
            System.out.println("Список сотрудников некорректен.");
        }
    }

    /**
     * Обновляет значение {@link #increment} и возвращает его.
     *
     * @return уникальное число
     */
    public static int inc() {
        SampleScript.increment += 1;
        return increment;
    }
}
