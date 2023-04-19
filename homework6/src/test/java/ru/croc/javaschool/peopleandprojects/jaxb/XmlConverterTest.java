package ru.croc.javaschool.peopleandprojects.jaxb;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.peopleandprojects.patterns.input.Manager;
import ru.croc.javaschool.peopleandprojects.patterns.input.Project;
import ru.croc.javaschool.peopleandprojects.patterns.input.ProjectList;
import ru.croc.javaschool.peopleandprojects.patterns.input.Subordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlConverterTest {

    @Test
    public void toXmlTest() throws JsonProcessingException {
        String expectedXml = "<Project><title>Проект 1</title><description>Описание</description><managers>" +
                "<manager name=\"m1\"><specialists><specialist name=\"Работник 1\"/><specialist name=\"Работник 2\"/>" +
                "</specialists></manager></managers></Project>";
        List<Subordinate> subordinates = new ArrayList<>(List.of(
                new Subordinate("Работник 1"),
                new Subordinate("Работник 2")
        ));
        List<Manager> managers = new ArrayList<>(List.of(
                new Manager("m1", subordinates)
        ));
        Project pr1 = new Project("Проект 1", "Описание", managers);

        XmlConverter xmlc = new XmlConverter();

        Assertions.assertEquals(expectedXml, xmlc.toXml(pr1));
    }

    @Test
    public void fromXmlTest() throws IOException {
        String xmlProjects = "<projects><project><title>Проект 1</title><description>Описание 1</description><managers>" +
                "<manager name=\"Человек 1\"><specialists><specialist name=\"Человек 3\"/>" +
                "<specialist name=\"Человек 4\"/></specialists></manager><manager name=\"Человек 2\"><specialists>" +
                "<specialist name=\"Человек 5\"/><specialist name=\"Человек 6\"/></specialists></manager></managers>" +
                "</project><project><title>Проект 2</title><description>Описание 2</description><managers>" +
                "<manager name=\"Человек 3\"><specialists><specialist name=\"Человек 5\"/>" +
                "<specialist name=\"Человек 6\"/></specialists></manager><manager name=\"Человек 4\"><specialists>" +
                "<specialist name=\"Человек 7\"/></specialists></manager></managers></project></projects>";
        XmlConverter xmlc = new XmlConverter();
        ProjectList projectList = xmlc.fromXml(xmlProjects, ProjectList.class);
        Assertions.assertEquals(xmlProjects, xmlc.toXml(projectList));
    }
}
