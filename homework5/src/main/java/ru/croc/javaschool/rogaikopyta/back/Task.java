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
     * Код.
     */
    private final int id;

    /**
     * Название.
     */
    private String name;

    /**
     * Описание.
     */
    private final String description;

    /**
     * Исполнитель.
     */
    private final String executor;

    /**
     * Статус.
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

    /**
     * Реализует строковое предстваление объекта {@link Task}.
     *
     * @return строковое представление задачи
     */
    @Override
    public String toString() {
        return String.format("%nЗадача № %s \"%s\"; %s; %s; %s",
                id,
                name,
                description,
                executor,
                status
        );
    }

    /**
     * Возвращает код.
     *
     * @return код
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название.
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * Задаёт название.
     *
     * @param name новое название задачи
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает статус.
     *
     * @return статус
     */
    public String getStatus() {
        return status;
    }

    /**
     * Задаёт статус.
     *
     * @param status новый статус задачи
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Возвращает описание.
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает исполнителя.
     *
     * @return исполнитель
     */
    public String getExecutor() {
        return executor;
    }
}
