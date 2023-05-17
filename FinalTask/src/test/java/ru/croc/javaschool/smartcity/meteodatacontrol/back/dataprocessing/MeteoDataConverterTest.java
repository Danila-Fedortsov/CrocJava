package ru.croc.javaschool.smartcity.meteodatacontrol.back.dataprocessing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.jaxb.XmlConverter;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.pressure.Pressure;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.pressure.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature.Temperature;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature.Temperatures;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.List;

/**
 * Тестирование преобразования данных.
 *
 * @author Danila Fedortsov
 */
public class MeteoDataConverterTest {

    /**
     * Тест преобразования данных из xml-модели в модель для работы с базой данных.
     */
    @Test
    public void fromXmlToDbModelTest() {
        String moment = "2023-05-15 10:00:00";
        double temp = 30.0;
        double pres = 760.4;
        var converter = new MeteoDataConverter();

        var actualTemp = new Temperature(temp, moment);
        var actualPres = new Pressure(pres, moment);
        var actualMeteoRecords = converter.fromXmlToDbModel(
                new Temperatures(List.of(actualTemp)),
                new Pressures(List.of(actualPres))
        );
        var expectedMeteoRecord = new MeteoRecord(Timestamp.valueOf(moment), temp, pres);

        Assertions.assertEquals(1, actualMeteoRecords.size());
        Assertions.assertEquals(
                expectedMeteoRecord.getMoment(),
                actualMeteoRecords.get(0).getMoment()
        );
        Assertions.assertEquals(
                expectedMeteoRecord.getTemperature(),
                actualMeteoRecords.get(0).getTemperature()
        );
        Assertions.assertEquals(
                expectedMeteoRecord.getPressure(),
                actualMeteoRecords.get(0).getPressure()
        );
    }

    /**
     * Тест получения списка температур.
     */
    @Test
    public void getTemperaturesTest() throws IOException {
        Path path = Path.of("src\\test\\resources\\TemperaturesTest.xml");
        var xmlConverter = new XmlConverter();
        var meteoDataConverter = new MeteoDataConverter();

        var actualTemp = xmlConverter.fromXmlFile(path, Temperatures.class).getTemperatureList();
        var expectedTemp = meteoDataConverter.getTemperatures(path).getTemperatureList();

        Assertions.assertEquals(
                actualTemp.size(),
                expectedTemp.size()
        );

        for (int i = 0; i < actualTemp.size(); i++) {
            var exp = expectedTemp.get(i);
            var act = actualTemp.get(i);

            Assertions.assertEquals(
                    exp.getMoment(),
                    act.getMoment()
            );

            Assertions.assertEquals(
                    exp.getValue(),
                    act.getValue()
            );
        }
    }

    /**
     * Тест получения списка давлений.
     */
    @Test
    public void getPressuresTest() throws IOException {
        Path path = Path.of("src\\test\\resources\\PressuresTest.xml");
        var xmlConverter = new XmlConverter();
        var meteoDataConverter = new MeteoDataConverter();

        var actualPres = xmlConverter.fromXmlFile(path, Pressures.class).getPressureList();
        var expectedPres = meteoDataConverter.getPressures(path).getPressureList();

        Assertions.assertEquals(
                actualPres.size(),
                expectedPres.size()
        );

        for (int i = 0; i < actualPres.size(); i++) {
            var exp = expectedPres.get(i);
            var act = actualPres.get(i);

            Assertions.assertEquals(
                    exp.getMoment(),
                    act.getMoment()
            );

            Assertions.assertEquals(
                    exp.getValue(),
                    act.getValue()
            );
        }
    }
}
