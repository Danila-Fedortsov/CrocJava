package ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.jaxb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature.Temperature;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature.Temperatures;

import java.io.IOException;
import java.nio.file.Path;

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
        final Path tempPath = Path.of("src\\test\\resources\\JaxbTest.xml");
        XmlConverter xmlConverter = new XmlConverter();

        var tempList = xmlConverter.fromXmlFile(
                tempPath,
                Temperatures.class
        ).getTemperatureList();

        Assertions.assertEquals(1, tempList.size());

        var expectedTemp = new Temperature(24.5, "2003-02-17 21:01:00.0");
        var actualTemp = tempList.get(0);

        Assertions.assertEquals(
                expectedTemp.getValue(),
                actualTemp.getValue()
        );
        Assertions.assertEquals(
                expectedTemp.getMoment(),
                expectedTemp.getMoment()
        );
    }
}
