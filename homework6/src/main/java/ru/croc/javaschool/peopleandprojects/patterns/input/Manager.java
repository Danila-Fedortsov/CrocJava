package ru.croc.javaschool.peopleandprojects.patterns.input;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Управляющий
 *
 * @author Danila Fedortsov
 */
public class Manager {

    /**
     * Имя.
     */
    @XmlAttribute
    String name;

    /**
     * Список подчинённых.
     */
    @XmlElementWrapper(name = "specialists")
    @XmlElement(name = "specialist")
    List<Subordinate> subordinates;

    /**
     * Создаёт {@link Manager}.
     *
     * @param name         имя
     * @param subordinates список подчинённых
     */
    public Manager(String name, List<Subordinate> subordinates) {
        this.name = name;
        this.subordinates = subordinates;
    }

    /**
     * Конструктор по умолчанию для сериализации и десериализации.
     */
    public Manager() {
    }

    public String getName() {
        return name;
    }

    /**
     * Возвращает список подчинённых.
     *
     * @return список подчинённых
     */
    public List<Subordinate> getSubordinates() {
        return subordinates;
    }
}
