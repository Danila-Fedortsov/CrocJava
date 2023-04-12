package ru.croc.javaschool.rogaikopyta.front;

import ru.croc.javaschool.rogaikopyta.back.Task;
import ru.croc.javaschool.rogaikopyta.back.TaskManagerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Map.entry;

/**
 * Пример использования класса {@link TaskManagerUtil} для пользовательского интерфейса в терминале.
 *
 * @author Danila Fedortsov
 */
public class TerminalGUI {
    /**
     * Название файла и директория по умолчанию.
     */
    private final String defaultPath = "tasks.dat";

    /**
     * Словарь со справочными сообщениями.
     */
    private final Map<String, String> USER_MESSAGES = new HashMap<>(Map.ofEntries(
            entry("-task", "Введите код задачи."),
            entry("-list", "Список задач:"),
            entry("-add", "Добавление.\n" +
                    "Введите код, название, описание, исполнителя и статус задачи, разделяя клавишей Enter."),
            entry("-del", "Удаление.\nВведите код задачи."),
            entry("-edit_name", "Редактирование.\nВведите код задачи, а затем новое имя."),
            entry("-edit_status", "Редактирование.\nВведите код задачи, а затем новый статус."),
            entry("-cancel", "Операция прервана."),
            entry("-exit", "Работа завершена."),
            entry("-clear", "Список задач очищен."),
            entry("-help",
                    "-task        --- Посмотреть подробности задачи по номеру.\n" +
                            "-list        --- Посмотреть список всех задач.\n" +
                            "-add         --- Добаваление новой задачи.\n" +
                            "-del         --- Удаление задачи.\n" +
                            "-edit_name   --- Редактирование названия задачи.\n" +
                            "-edit_status --- Редактирование статуса задачи.\n" +
                            "-cancel      --- Прервать любую операцию.\n" +
                            "-clear       --- Очистить список задач.\n" +
                            "-exit        --- Завершить работу.\n" +
                            "-help        --- Вызов подсказок."
            )
    ));

    /**
     * Словарь с информационными сообщениями о выполнении программы.
     */
    private final Map<String, String> RUNTIME_MESSAGES = new HashMap<>(Map.ofEntries(
            entry("new_file", "Создан новый файл " + defaultPath + ", так как старый не был найден."),
            entry("start", "Task manager v1.0"),
            entry("ref", "Введите команду. Вызов подсказки: -help"),
            entry("task_err", "Задачи с таким кодом нет."),
            entry("list_empty", "На данный момент задач нет."),
            entry("add_suc", "Задача успешно добавлена!"),
            entry("add_err", "Что-то пошло не так!\nВероятнее всего задача с таким кодом уже существует."),
            entry("del_suc", "Задача успешно удалена!"),
            entry("del_err", "Что-то пошло не так!\nВероятнее всего задача с заданным кодом не найдена."),
            entry("edit_suc", "Редактирование прошло успешно."),
            entry("edit_err", "Что-то пошло не так!\nВероятнее всего задача с заданным кодом не найдена."),
            entry("id_err", "Введите целочисленное значение! Завершить операцию: -cancel"),
            entry("input_err", "Некорректная команда. Попробуйте снова."),
            entry("separator", "\n|>======================================================<|")
    ));

    /**
     * Управляющий нашим таск-менеджером.
     */
    TaskManagerUtil taskManagerUtil;

    /**
     * Создаёт {@link TerminalGUI}.
     */
    public TerminalGUI() {
        this.taskManagerUtil = new TaskManagerUtil(defaultPath);
        if (taskManagerUtil.fileIsNew()) {
            printRuntimeMessage("new_file");
        }
    }

    /**
     * Обработчик ввода в терминал.
     */
    public void handler() {
        Scanner scanner = new Scanner(System.in);

        printRuntimeMessage("start");
        printRuntimeMessage("separator");
        printRuntimeMessage("ref");

        String inputLine = scanner.nextLine();

        while (!inputLine.equals("-exit")) {
            switch (inputLine) {
                case "-task": {
                    printUserMessage(inputLine);
                    inputLine = scanner.nextLine();

                    while (idNotCorrect(inputLine) && !inputLine.equals("-cancel")) {
                        inputLine = scanner.nextLine();
                    }

                    if (inputLine.equals("-cancel")) {
                        printUserMessage("-cancel");
                        break;
                    }

                    getTask(Integer.parseInt(inputLine));
                    break;
                }
                case "-list": {
                    getTaskList();
                    break;
                }
                case "-add": {
                    printUserMessage(inputLine);
                    int count = 5;
                    ArrayList<String> data = new ArrayList<>();


                    do {
                        inputLine = scanner.nextLine();
                        if (count == 5 && idNotCorrect(inputLine)) {
                            continue;
                        }
                        data.add(inputLine);
                        count--;
                    } while (!inputLine.equals("-cancel") && count > 0);

                    if (inputLine.equals("-cancel")) {
                        printUserMessage(inputLine);
                        break;
                    }

                    appendTask(data);
                    break;
                }
                case "-del": {
                    printUserMessage(inputLine);
                    inputLine = scanner.nextLine();

                    while (idNotCorrect(inputLine) && !inputLine.equals("-cancel")) {
                        inputLine = scanner.nextLine();
                    }

                    if (inputLine.equals("-cancel")) {
                        printUserMessage(inputLine);
                        break;
                    }

                    removeTask(Integer.parseInt(inputLine));
                    break;
                }
                case "-edit_name": {
                    printUserMessage(inputLine);
                    inputLine = scanner.nextLine();
                    while (idNotCorrect(inputLine) && !inputLine.equals("-cancel")) {
                        inputLine = scanner.nextLine();
                    }
                    if (inputLine.equals("-cancel")) {
                        printUserMessage("-cancel");
                        break;
                    }

                    int id = Integer.parseInt(inputLine);
                    inputLine = scanner.nextLine();

                    if (inputLine.equals("-cancel")) {
                        printUserMessage("-cancel");
                        break;
                    }

                    editName(id, inputLine);
                    break;
                }
                case "-edit_status": {
                    printUserMessage(inputLine);
                    inputLine = scanner.nextLine();
                    while (idNotCorrect(inputLine) && !inputLine.equals("-cancel")) {
                        inputLine = scanner.nextLine();
                    }
                    if (inputLine.equals("-cancel")) {
                        printUserMessage("-cancel");
                        break;
                    }

                    int id = Integer.parseInt(inputLine);
                    inputLine = scanner.nextLine();

                    if (inputLine.equals("-cancel")) {
                        printUserMessage("-cancel");
                        break;
                    }

                    editStatus(id, inputLine);
                    break;
                }
                case "-clear": {
                    printUserMessage("-clear");
                    taskManagerUtil.clearTaskList();
                    break;
                }
                case "-help": {
                    printUserMessage(inputLine);
                    break;
                }
                default: {
                    printRuntimeMessage("input_err");
                    break;
                }
            }

            printRuntimeMessage("separator");
            printRuntimeMessage("ref");
            inputLine = scanner.nextLine();
        }
    }

    /**
     * Добавляет задачу в список задач. На вход подаётся список из 5 строк, где первый элемент приводим к целому числу.
     *
     * @param data данные о задаче
     */
    private void appendTask(ArrayList<String> data) {
        Task newTask = new Task(
                Integer.parseInt(data.get(0)),
                data.get(1),
                data.get(2),
                data.get(3),
                data.get(4)
        );

        if (this.taskManagerUtil.addTask(newTask)) {
            printRuntimeMessage("add_suc");
        } else {
            printRuntimeMessage("add_err");
        }
    }

    /**
     * Удаляет задачу с кодом id.
     *
     * @param id код задачи для удаления
     */
    private void removeTask(int id) {
        if (this.taskManagerUtil.delTask(id)) {
            printRuntimeMessage("del_suc");
        } else {
            printRuntimeMessage("del_err");
        }
    }

    /**
     * Изменяет название задачи с кодом id.
     *
     * @param id   код задачи для изменения
     * @param name новое название
     */
    private void editName(int id, String name) {
        if (this.taskManagerUtil.setTaskName(id, name)) {
            printRuntimeMessage("edit_suc");
        } else {
            printRuntimeMessage("edit_err");
        }
    }

    /**
     * Изменяет статус задачи с кодом id.
     *
     * @param id     код задачи для изменения
     * @param status новый статус
     */
    private void editStatus(int id, String status) {
        if (this.taskManagerUtil.setTaskStatus(id, status)) {
            printRuntimeMessage("edit_suc");
        } else {
            printRuntimeMessage("edit_err");
        }
    }

    /**
     * Выводит на экран задачу с кодом id.
     *
     * @param id код задачи
     */
    private void getTask(int id) {
        Task task = taskManagerUtil.getTaskById(id, taskManagerUtil.getTasks());
        if (Objects.isNull(task)) {
            printRuntimeMessage("task_err");
            return;
        }
        printInformation(task.toString());
    }

    /**
     * Возвращает список задач.
     */
    private void getTaskList() {
        LinkedList<Task> list = taskManagerUtil.getTasks();
        if (list.isEmpty()) {
            printRuntimeMessage("list_empty");
            return;
        }
        printInformation(list
                .toString()
                .substring(1, list.toString().length() - 2)
        );
    }

    /**
     * Проверяет может ли строка быть кодом задачи.
     *
     * @param value строка
     * @return true - не может быть id, false - может быть id
     */
    private boolean idNotCorrect(String value) {
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            printRuntimeMessage("id_err");
            return true;
        }
    }

    /**
     * Выводит сообщение inf на экран.
     *
     * @param inf информация для вывода на экран.
     */
    private void printInformation(String inf) {
        System.out.println(inf);
    }

    /**
     * Выводит справочное сообщение с кодом code на экран.
     *
     * @param code код справочного сообщения
     */
    private void printUserMessage(String code) {
        printInformation(USER_MESSAGES.get(code));
    }

    /**
     * Выводит информационное сообщение о работе программы с кодом code.
     *
     * @param code код информационного сообщения
     */
    private void printRuntimeMessage(String code) {
        printInformation(RUNTIME_MESSAGES.get(code));
    }
}