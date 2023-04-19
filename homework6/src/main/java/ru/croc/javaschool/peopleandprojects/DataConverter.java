package ru.croc.javaschool.peopleandprojects;

import ru.croc.javaschool.peopleandprojects.jaxb.XmlConverter;
import ru.croc.javaschool.peopleandprojects.patterns.input.Manager;
import ru.croc.javaschool.peopleandprojects.patterns.input.ProjectList;
import ru.croc.javaschool.peopleandprojects.patterns.input.Subordinate;
import ru.croc.javaschool.peopleandprojects.patterns.output.Person;
import ru.croc.javaschool.peopleandprojects.patterns.output.PersonList;
import ru.croc.javaschool.peopleandprojects.patterns.input.Project;
import ru.croc.javaschool.peopleandprojects.patterns.output.PersonProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Преобразователь данных.
 *
 * @author Danila Fedortsov
 */
public class DataConverter {

    /**
     * Создвёт {@link DataConverter}.
     */
    public DataConverter() {
    }

    /**
     * Возвращает строку с данными в формате xml.
     *
     * @param projects строка в формате xml с данными по проектам
     * @return строка в формате xml с данными по людям
     * @throws IOException бывает конечно
     */
    public String projectsToPeopleXml(String projects) throws IOException {
        XmlConverter xmlc = new XmlConverter();
        ProjectList projectList = xmlc.fromXml(projects, ProjectList.class);
        PersonList personList = projectsToPeople(projectList);

        return xmlc.toXml(personList);
    }

    /**
     * Возвращает строку с данными в формате xml в читаемом виде.
     *
     * @param projects строка в формате xml с данными по проектам
     * @return строка в формате xml с данными по людям в читаемом виде
     * @throws IOException бывает конечно
     */
    public String projectsToPeopleClearXml(String projects) throws IOException {
        XmlConverter xmlc = new XmlConverter();
        ProjectList projectList = xmlc.fromXml(projects, ProjectList.class);
        PersonList personList = projectsToPeople(projectList);

        return xmlc.toClearXml(personList);
    }

    /**
     * Преобразует список проектов в список людей, работавших над этими проектами.
     *
     * @param projects список проектов
     * @return список людей
     */
    private PersonList projectsToPeople(ProjectList projects) {
        Map<String, ArrayList<PersonProject>> personMap = new TreeMap<>();
        PersonList answer = new PersonList();

        for (Project project : projects.getProjects()) {
            for (Manager manager : project.getManagers()) {
                PersonProject personProject = new PersonProject(
                        project.getTitle(),
                        "Менеджер",
                        ""
                );
                personMap.putIfAbsent(
                        manager.getName(),
                        new ArrayList<>()
                );
                personMap.get(manager.getName()).add(personProject);

                for (Subordinate sub : manager.getSubordinates()) {
                    personProject = new PersonProject(
                            project.getTitle(),
                            "Специалист",
                            manager.getName()
                    );
                    personMap.putIfAbsent(
                            sub.getName(),
                            new ArrayList<>()
                    );
                    personMap.get(sub.getName()).add(personProject);
                }
            }
        }

        for (String key : personMap.keySet()) {
            answer.addPerson(new Person(key, personMap.get(key)));
        }

        return answer;
    }
}
