package ru.croc.javaschool.peopleandprojects.patterns.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Список людей. Класс-оболочка.
 *
 * @author Danila Fedortsov
 */
@XmlRootElement(name = "people")
public class PersonList {
    /**
     * Список людей.
     */
    @XmlElement(name = "person")
    private List<Person> personList;

    /**
     * Добавляет человека в список людей.
     *
     * @param p человек
     */
    public void addPerson(Person p) {
        if (Objects.isNull(personList)) {
            this.personList = new ArrayList<>();
        }
        this.personList.add(p);
    }
}
