package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.property;

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
     * Тест {@link PropertyContainer#loadProperties}.
     *
     * @throws IOException ошибка ввода
     */
    @Test
    public void loadPropertiesTest() throws IOException {
        PropertyContainer.loadProperties("test.properties");

        Assertions.assertEquals("stupid_city", PropertyContainer.getProperty("database.name"));
    }
}
