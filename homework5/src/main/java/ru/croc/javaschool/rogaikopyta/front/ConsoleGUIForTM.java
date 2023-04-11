package ru.croc.javaschool.rogaikopyta.front;

import ru.croc.javaschool.rogaikopyta.back.Task;
import ru.croc.javaschool.rogaikopyta.back.TaskManagerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Map.entry;

public class ConsoleGUIForTM {
    private final String defaultPath = "src/main/resources/tasks.dat";

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
                            "-exit        --- Завершить работу.")
    ));

    private final Map<String, String> RUNTIME_MESSAGES = new HashMap<>(Map.ofEntries(
            entry("start", "Task manager v1.0"),
            entry("ref", "Для вызова списка команд введите команду -help"),
            entry("init_err", "Непредвиденная ошибка! Вероятнее всего отсутствует папка resources.\n"
                    + "Убедитесь, что путь \"~/src/main/resources/\" существует и запустите программу снова."),
            entry("task_err", "Задачи с таким кодом нет."),
            entry("list_empty", "На данный момент задач нет."),
            entry("add_suc", "Задача успешно добавлена!"),
            entry("add_err", "Что-то пошло не так!\nВероятнее всего задача с таким кодом уже существует."),
            entry("del_suc", "Задача успешно удалена!"),
            entry("del_err", "Что-то пошло не так!\nВероятнее всего задача с заданным кодом не найдена."),
            entry("edit_suc", "Редактирование прошло успешно."),
            entry("edit_err", "Что-то пошло не так!\nВероятнее всего задача с заданным кодом не найдена."),
            entry("id_err", "Введите целочисленное значение! Завершить операцию: -cancel"),
            entry("input_err", "Введите одну из команд. Для справки введите -help"),
            entry("separator", "\n|>======================================================<|")
    ));
    TaskManagerUtil taskManagerUtil;

    public ConsoleGUIForTM() {
        try {
            this.taskManagerUtil = new TaskManagerUtil(defaultPath);
        } catch (IOException ex) {
            printRuntimeMessage("init_err");
            throw new RuntimeException(ex);
        }
    }

//    public ConsoleGUIForTM(String path) {
//        this();
//        try {
//            this.taskManagerUtil = new TaskManagerUtil(path);
//        } catch (IOException e) {
//            String message = String.format(
//                    "Вероятнее всего передан некорректный путь до файла. " +
//                            "Был создан файл по умолчанию \"%s\".%n" +
//                            "Для указания нужного файла воспользуйтесь методом setPath.",
//                    defaultPath
//            );
//            printMessage(message);
//        }
//    }

    public void handler() {
        Scanner scanner = new Scanner(System.in);

        printRuntimeMessage("separator");
        printRuntimeMessage("start");
        printRuntimeMessage("separator");
        printRuntimeMessage("ref");

        String inputLine = scanner.nextLine();

        while (!inputLine.equals("-exit")) {
            switch (inputLine) {
                case "-task": {
                    printUserMessage(inputLine);
                    inputLine = scanner.nextLine();

                    while (!isIdCorrect(inputLine) && !inputLine.equals("-cancel")) {
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
                        if (count == 5 && !isIdCorrect(inputLine)) {
                            inputLine = scanner.nextLine();
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

                    while (!isIdCorrect(inputLine) && !inputLine.equals("-cancel")) {
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
                    while (!isIdCorrect(inputLine) && !inputLine.equals("-cancel")) {
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
                    while (!isIdCorrect(inputLine) && !inputLine.equals("-cancel")) {
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

    private void removeTask(int id) {
        if (this.taskManagerUtil.delTask(id)) {
            printRuntimeMessage("del_suc");
        } else {
            printRuntimeMessage("del_err");
        }
    }

    private void editName(int id, String name) {
        if (this.taskManagerUtil.setTaskName(id, name)) {
            printRuntimeMessage("edit_suc");
        } else {
            printRuntimeMessage("edit_err");
        }
    }

    private void editStatus(int id, String status) {
        if (this.taskManagerUtil.setTaskStatus(id, status)) {
            printRuntimeMessage("edit_suc");
        } else {
            printRuntimeMessage("edit_err");
        }
    }

    private void getTask(int id) {
        Task task = taskManagerUtil.getTaskById(id, taskManagerUtil.getTasks());
        if (Objects.isNull(task)) {
            printRuntimeMessage("task_err");
            return;
        }
        printInformation(task.toString());
    }

    private void getTaskList() {
        LinkedList<Task> list = taskManagerUtil.getTasks();
        if (list.isEmpty()) {
            printRuntimeMessage("list_empty");
            return;
        }
        printInformation(list.toString());
    }

    private boolean isIdCorrect(String value) {
        try {
            int id = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            printRuntimeMessage("id_err");
            return false;
        }
    }

    private void printInformation(String inf) {
        System.out.println(inf);
    }

    private void printUserMessage(String code) {
        System.out.println(USER_MESSAGES.get(code));
    }

    private void printRuntimeMessage(String code) {
        System.out.println(RUNTIME_MESSAGES.get(code));
    }
}