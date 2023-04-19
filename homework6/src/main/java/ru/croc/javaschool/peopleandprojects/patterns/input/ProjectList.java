package ru.croc.javaschool.peopleandprojects.patterns.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список проектов. Класс-оболочка.
 *
 * @author Danila Fedortsov
 */
@XmlRootElement(name = "projects")
public class ProjectList {
    /**
     * Список проектов.
     */
    @XmlElement(name = "project")
    private List<Project> projects;

    /**
     * Возвращает список проектов.
     *
     * @return список проектов
     */
    public List<Project> getProjects() {
        return projects;
    }
}
