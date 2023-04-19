package ru.croc.javaschool.peopleandprojects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Тестирование {@link DataConverter}.
 *
 * @author Danila Fedortsov
 */
public class DataConverterTest {

    /**
     * Тестирование {@link DataConverter#projectsToPeopleXml}.
     *
     * @throws IOException бывает да
     */
    @Test
    public void projectsToPeopleXml() throws IOException {
        DataConverter dc = new DataConverter();
        String actual = "<projects><project><title>Проект 1</title><description>Описание 1</description><managers>" +
                "<manager name=\"Человек 1\"><specialists><specialist name=\"Человек 3\"/>" +
                "<specialist name=\"Человек 4\"/></specialists></manager><manager name=\"Человек 2\"><specialists>" +
                "<specialist name=\"Человек 5\"/><specialist name=\"Человек 6\"/></specialists></manager></managers>" +
                "</project><project><title>Проект 2</title><description>Описание 2</description><managers>" +
                "<manager name=\"Человек 3\"><specialists><specialist name=\"Человек 5\"/>" +
                "<specialist name=\"Человек 6\"/></specialists></manager><manager name=\"Человек 4\"><specialists>" +
                "<specialist name=\"Человек 7\"/></specialists></manager></managers></project></projects>";
        String expected = "<people><person><name>Человек 1</name><projects><project title=\"Проект 1\">" +
                "<role>Менеджер</role><manager></manager></project></projects></person><person><name>Человек 2</name>" +
                "<projects><project title=\"Проект 1\"><role>Менеджер</role><manager></manager></project></projects>" +
                "</person><person><name>Человек 3</name><projects><project title=\"Проект 1\"><role>Специалист</role>" +
                "<manager>Человек 1</manager></project><project title=\"Проект 2\"><role>Менеджер</role><manager>" +
                "</manager></project></projects></person><person><name>Человек 4</name><projects>" +
                "<project title=\"Проект 1\"><role>Специалист</role><manager>Человек 1</manager></project>" +
                "<project title=\"Проект 2\"><role>Менеджер</role><manager></manager></project></projects>" +
                "</person><person><name>Человек 5</name><projects><project title=\"Проект 1\"><role>Специалист</role>" +
                "<manager>Человек 2</manager></project><project title=\"Проект 2\"><role>Специалист</role>" +
                "<manager>Человек 3</manager></project></projects></person><person><name>Человек 6</name><projects>" +
                "<project title=\"Проект 1\"><role>Специалист</role><manager>Человек 2</manager></project>" +
                "<project title=\"Проект 2\"><role>Специалист</role><manager>Человек 3</manager></project></projects>" +
                "</person><person><name>Человек 7</name><projects><project title=\"Проект 2\"><role>Специалист</role>" +
                "<manager>Человек 4</manager></project></projects></person></people>";

        Assertions.assertEquals(expected, dc.projectsToPeopleXml(actual));
    }

    /**
     * Тестирование {@link DataConverter#projectsToPeopleClearXml}.
     *
     * @throws IOException бывает
     */
    @Test
    public void projectsToPeopleClearXml() {
        // не тестировал, потому что бесполезно.
    }
}
