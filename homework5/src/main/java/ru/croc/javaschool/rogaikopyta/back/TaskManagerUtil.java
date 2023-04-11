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
    private String path;

    public TaskManagerUtil(String path) throws IOException {
        File tasks = new File(path);
        tasks.createNewFile();
        this.path = path;
    }

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

    public boolean delTask(int id) {
        LinkedList<Task> taskList = getTasks();
        Task task = getTaskById(id, taskList);

        if (taskList.remove(task)) {
            setTasks(taskList);
            return true;
        }

        return false;
    }

    public Task getTaskById(int id, LinkedList<Task> taskList) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public LinkedList<Task> getTasks() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            Object taskList = inputStream.readObject();
            return (LinkedList<Task>) taskList;
        } catch (ClassNotFoundException | EOFException ex1) {
            return new LinkedList<>();
        } catch (IOException ex3) {
            throw new RuntimeException(ex3);
        }
    }

    public void setTasks(LinkedList<Task> newTaskList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(newTaskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTaskList() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(new LinkedList<Task>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
