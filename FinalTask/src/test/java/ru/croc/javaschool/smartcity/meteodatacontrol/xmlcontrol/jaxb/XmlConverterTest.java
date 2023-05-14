package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperatures;

import java.io.IOException;

public class XmlConverterTest {

    private final String tempPath = "src\\test\\resources\\TemperaturesTest.xml";


    @Test
    public void getMeasurementListFromFileTest() throws IOException {
        String expected = "Temperature{value=24.5, moment='21:01:00 17.02.2003'}";
        XmlConverter xmlConverter = new XmlConverter();
        Temperatures temperatureList = xmlConverter.fromXmlFile(
                tempPath,
                Temperatures.class
        );

        Assertions.assertEquals(
                expected,
                temperatureList
                        .getTemperatureList()
                        .get(0)
                        .toString()
        );
    }
}
