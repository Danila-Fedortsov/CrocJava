package ru.croc.javaschool.task1.employment;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Работа с данными и управление персоналом.
 *
 * @author Danila Fedortsov
 */
public class EmployeeManagement {
    /**
     * Принимает на вход смешанный список сотрудников и распределяет их по компаниям. Возвращает список списков
     * сотрудников (список организаций), в которых первый идущий по порядку сотрудник является самым главным в
     * организации. Возвращает null если список не прошел проверку на корректность {@link #isCorrectEmployeeList}.
     *
     * @param employees список сотрудников
     * @return список компаний (список списков сотрудников), либо null
     */
    public static ArrayList<ArrayList<Employee>> getCompanies(ArrayList<Employee> employees) {
        if (!isCorrectEmployeeList(employees)) {
            return null;
        }

        ArrayList<ArrayList<Employee>> answer = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getManager() == null) {
                ArrayList<Employee> company = new ArrayList<>();
                company.add(employee);
                answer.add(company);
            }
        }

        for (Employee employee : employees) {
            boolean inCompany = false;
            for (List<Employee> company : answer) {
                if (company.contains(employee)) {
                    inCompany = true;
                    break;
                }
            }

            if (!inCompany) {
                Employee interimObj = employee;
                while (interimObj.getManager() != null) {
                    interimObj = interimObj.getManager();
                }

                for (List<Employee> company : answer) {
                    if (company.get(0).getId().equals(interimObj.getId())) {
                        company.add(employee);
                    }
                }
            }
        }

        return answer;
    }

    /**
     * Принимает на вход смешанный список сотрудников и подсчитывает сколько сотрудников в подчинении у каждого
     * начальника. Возвращает отсортированный по значению словарь, где ключ - это начальник, а значение - количество
     * сотрудников в подчинении. Возвращает null если список не прошёл проверку на корректность
     * {@link #isCorrectEmployeeList}.
     *
     * @param employees список сотрудников
     * @return словарь, где ключ - начальник, значение - количество подчинённых, либо null
     */
    public static HashMap<Employee, Integer> getManagersWithNumOfSub(ArrayList<Employee> employees) {
        if (!isCorrectEmployeeList(employees)) {
            return null;
        }
        TreeMap<Employee, Integer> answer = new TreeMap<>();
        for (Employee e : employees) {
            if (e.getManager() == null && answer.get(e) == null) {
                answer.put(e, 0);
            }

            if (e.getManager() != null) {
                answer.putIfAbsent(e.getManager(), 0);
            }
        }

        for (Employee e : employees) {
            if (e.getManager() != null && answer.get(e) == null) {
                Employee manager = e.getManager();
                int num = 1;
                while (manager != null) {
                    num += answer.get(manager);
                    answer.put(manager, num);
                    manager = manager.getManager();
                }
            }
        }

        return answer.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }

    /**
     * Метод проверяет список сотрудников на корректность. В первую очередь метод делает стандартные проверки на null
     * (Если null вместо списка или вместо одного из элементов списка). А так же проверяет ориентированный граф
     * сотрудников на кольца (например когда 2 сотрудника указаны друг у друга как начальники). Если колец нет,
     * то можно гарантировать, что список корректен, тк в таком случае всегда найдётся хотя бы один самый главный
     * сотрудник являющийся вершиной нашего иерархического дерева.
     *
     * @param employees список сотрудников
     * @return true - спискок корректен, false - список некорректен.
     */
    public static boolean isCorrectEmployeeList(ArrayList<Employee> employees) {
        // Проверка на null.
        if (employees == null || employees.contains(null)) {
            return false;
        }

        // Проверка на отсутсвие колец в ориентированном графе сотрудников. Если отсутсвуют кольца, значит у каждого
        // сотрудника обязательно есть самый главный начальник.
        boolean haveRing = false;
        for (Employee e : employees) {
            Employee interimObj = e;
            ArrayList<Integer> ids = new ArrayList<>();
            while (!haveRing && interimObj.getManager() != null) {
                haveRing = ids.contains(interimObj.getId());
                ids.add(interimObj.getId());
                interimObj = interimObj.getManager();
            }
            if (haveRing) {
                break;
            }
        }
        return !haveRing;
    }
}
