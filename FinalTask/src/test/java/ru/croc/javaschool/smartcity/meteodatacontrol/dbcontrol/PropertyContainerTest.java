package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.property.PropertyContainer;

import java.io.IOException;


public class PropertyContainerTest {
    @Test
    public void loadPropertiesTest() throws IOException {
        PropertyContainer.loadProperties("test.properties");

        Assertions.assertEquals("stupid_city", PropertyContainer.getProperty("database.name"));
    }
}
