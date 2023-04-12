package ru.croc.javaschool.rogaikopyta.back;

import java.io.File;
import java.io.IOException;
import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Управление задачами.
 *
 * @author Danila Fedortsov
 */
public class TaskManagerUtil {
    /**
     * Полное или относительное имя файла (относительно папки src).
     */
    private final String path;

    /**
     * Был ли задан новый файл.
     */
    private final boolean fileIsNew;

    /**
     * Создаёт {@link TaskManagerUtil}. Создаёт новый файл с именем path, если такого файла не найдено.
     *
     * @param path полное или относительное имя файла (относительно папки src)
     * @throws RuntimeException несуществующая директория в {@link #path}
     */
    public TaskManagerUtil(String path) {
        try {
            File tasks = new File(path);
            this.fileIsNew = tasks.createNewFile();
            this.path = path;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Задаёт новое имя для задачи с кодом id.
     *
     * @param id   код задачи
     * @param name новое название задачи
     * @return true - имя успешно изменено, false - имя не изменено (задачи с кодом id нет)
     */
    public boolean setTaskName(int id, String name) {
        LinkedList<Task> taskList = getTasks();
        Task task = getTaskById(id, taskList);
        if (Objects.isNull(task)) {
            return false;
        }
        task.setName(name);
        setTasks(taskList);
        return true;
    }

    /**
     * Задаёт новый статус для задачи с кодом id.
     *
     * @param id     код задачи
     * @param status новый статус задачи
     * @return true - статус успешно изменён, false - статус не изменён (задачи с кодом id нет)
     */
    public boolean setTaskStatus(int id, String status) {
        LinkedList<Task> taskList = getTasks();
        Task task = getTaskById(id, taskList);
        if (Objects.isNull(task)) {
            return false;
        }
        task.setStatus(status);
        setTasks(taskList);
        return true;
    }

    /**
     * Добавляет новую задачу в список.
     *
     * @param task новая задача
     * @return true - задача успешно добавлена, false - задача не добавлена
     */
    public boolean addTask(Task task) {
        LinkedList<Task> taskList = getTasks();
        if (Objects.isNull(taskList) || Objects.isNull(task)) {
            return false;
        }
        if (Objects.nonNull(getTaskById(task.getId(), getTasks()))) {
            return false;
        }
        taskList.add(task);
        setTasks(taskList);
        return true;
    }

    /**
     * Удаляет из списка задачу с кодом id.
     *
     * @param id код удаляемой задачи
     * @return true - задача успешно удалена, false - задача не удалена (задачи с кодом id нет)
     */
    public boolean delTask(int id) {
        LinkedList<Task> taskList = getTasks();
        Task task = getTaskById(id, taskList);

        if (taskList.remove(task)) {
            setTasks(taskList);
            return true;
        }

        return false;
    }

    /**
     * Возвращает задачу из списка по коду id. Вернет null, если такой задачи нет.
     *
     * @param id       номер искомой задачи
     * @param taskList список задач
     * @return задача, либо null
     */
    public Task getTaskById(int id, LinkedList<Task> taskList) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Возвращает список задач из файла. В случае, если файл оказался пустым, вернёт пустой LinkedList.
     *
     * @return список задач
     * @throws ClassCastException не понял в каком случае вызывается
     */
    public LinkedList<Task> getTasks() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            Object taskList = inputStream.readObject();
            return (LinkedList<Task>) taskList;
        } catch (EOFException ex1) {
            return new LinkedList<>();
        } catch (ClassNotFoundException | IOException ex2) {
            throw new RuntimeException(ex2);
        }
    }

    /**
     * Перезаписывает список задач в файл.
     *
     * @param newTaskList новый список задач
     */
    public void setTasks(LinkedList<Task> newTaskList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(newTaskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записывает пустой список в файл, тем самым очищая список задач.
     */
    public void clearTaskList() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(new LinkedList<Task>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return true - был создан новый файл, false - найден старый файл
     */
    public boolean fileIsNew() {
        return fileIsNew;
    }
}
