package ru.croc.javaschool.peopleandprojects.patterns.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Человек.
 *
 * @author Danila Fedortsov
 */
public class Person {
    /**
     * Имя.
     */
    @XmlElement
    private String name;

    /**
     * Список проектов.
     */
    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<PersonProject> personProjects;

    /**
     * Создаёт {@link Person}.
     *
     * @param name           имя
     * @param personProjects список проектов
     */
    public Person(String name, List<PersonProject> personProjects) {
        this.name = name;
        this.personProjects = personProjects;
    }

    /**
     * Конструктор по умолчанию для сериализации и десериализации.
     */
    public Person() {
    }
}
