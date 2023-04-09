package ru.croc.javaschool.rogaikopyta.tasksback;

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
    private int id;

    /**
     * Название задачи.
     */
    private String name;

    /**
     * Описание задачи.
     */
    private String description;

    /**
     * Исполнитель.
     */
    private String executor;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
