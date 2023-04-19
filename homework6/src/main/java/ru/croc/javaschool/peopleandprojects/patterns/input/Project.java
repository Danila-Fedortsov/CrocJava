package ru.croc.javaschool.peopleandprojects.patterns.input;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Проект.
 *
 * @author Danila Fedortsov
 */
public class Project {
    /**
     * Название.
     */
    @XmlElement
    private String title;

    /**
     * Описание.
     */
    @XmlElement
    private String description;

    /**
     * Список управляющих.
     */
    @XmlElementWrapper
    @XmlElement(name = "manager")
    private List<Manager> managers;

    /**
     * Создаёт {@link Project}.
     *
     * @param title       название
     * @param description описание
     * @param managers    список менеджеров
     */
    public Project(String title, String description, List<Manager> managers) {
        this.title = title;
        this.description = description;
        this.managers = managers;
    }

    /**
     * Конструктор по умолчанию для сериализации и десериализации.
     */
    public Project() {
    }

    /**
     * Возвращает название.
     *
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает список менеджеров.
     *
     * @return список менеджеров
     */
    public List<Manager> getManagers() {
        return managers;
    }
}
