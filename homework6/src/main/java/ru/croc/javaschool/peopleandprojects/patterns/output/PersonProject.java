package ru.croc.javaschool.peopleandprojects.patterns.output;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Проект, в котором человек принимал участие.
 *
 * @author Danila Fedortsov
 */
public class PersonProject {
    /**
     * Название.
     */
    @XmlAttribute
    private String title;

    /**
     * Роль человека в проекте.
     */
    @XmlElement
    private String role;

    /**
     * Управляющий проектом.
     */
    @XmlElement
    private String manager;

    /**
     * Создаёт {@link PersonProject}.
     *
     * @param title   название
     * @param role    роль
     * @param manager управляющи
     */
    public PersonProject(String title, String role, String manager) {
        this.title = title;
        this.role = role;
        this.manager = manager;
    }

    /**
     * Конструктор по умолчанию для сериализации и десериализации.
     */
    public PersonProject() {
    }
}
