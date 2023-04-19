package ru.croc.javaschool.peopleandprojects.patterns.input;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Подчинённый.
 *
 * @author Danila Fedortsov
 */
public class Subordinate {
    /**
     * Имя.
     */
    @XmlAttribute
    String name;

    /**
     * Создаёт {@link Subordinate}.
     *
     * @param name имя
     */
    public Subordinate(String name) {
        this.name = name;
    }

    /**
     * Конструктор по умолчанию для сериализации и десериализации.
     */
    public Subordinate() {
    }

    /**
     * Возвращает имя.
     *
     * @return имя
     */
    public String getName() {
        return name;
    }
}
