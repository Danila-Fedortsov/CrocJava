package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperatures;

import java.io.IOException;

/**
 * Тест xml-конвертера.
 *
 * @author Danila Fedortsov
 */
public class XmlConverterTest {

    /**
     * Тест функции возвращающей объект из xml-файла.
     *
     * @throws IOException ошибка ввода
     */
    @Test
    public void getMeasurementListFromFileTest() throws IOException {
        final String tempPath = "src\\test\\resources\\JaxbTest.xml";
        String expected = "Temperature{value=24.5, moment='2003-02-17 21:01:00.0'}";
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
