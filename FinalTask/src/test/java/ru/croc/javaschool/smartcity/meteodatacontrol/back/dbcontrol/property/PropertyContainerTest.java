package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Тест хранилища свойств проекта.
 *
 * @author Danila Fedortsov
 */
public class PropertyContainerTest {
    /**
     * Тест хранилища свойств.
     *
     * @throws IOException ошибка ввода
     */
    @Test
    public void loadPropertiesTest() throws IOException {
        PropertyContainer container = new PropertyContainer("test.properties");

        Assertions.assertEquals("stupid_city_db", container.getProperty("database.name"));
        Assertions.assertEquals("root", container.getProperty("database.username"));
        Assertions.assertEquals("root", container.getProperty("database.password"));

    }
}
