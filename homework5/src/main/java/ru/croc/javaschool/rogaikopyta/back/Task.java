package ru.croc.javaschool.rogaikopyta.back;

import java.io.Serializable;

/**
 * Задача.
 *
 * @author Danila Fedortsov
 */
public class Task implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 2002L;

    /**
     * Код задачи.
     */
    private final int id;

    /**
     * Название задачи.
     */
    private String name;

    /**
     * Описание задачи.
     */
    private final String description;

    /**
     * Исполнитель.
     */
    private final String executor;

    /**
     * Статус задачи.
     */
    private String status;

    /**
     * Создаёт {@link Task}.
     *
     * @param id          код
     * @param name        название
     * @param description описание
     * @param executor    исполнитель
     * @param status      статус
     */
    public Task(int id, String name, String description, String executor, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача " + id +
                " \"" + name + "\";" +
                "Описание: " + description + "; " +
                "Исполнитель: " + executor + "; " +
                "Статус: " + status + ".";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public String getExecutor() {
        return executor;
    }
}
