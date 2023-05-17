package ru.croc.javaschool.smartcity.meteodatacontrol.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.dsprovider.impl.DerbyDataSourceProvider;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.property.PropertyContainer;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.repository.impl.DerbyMeteoRepository;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.service.MeteoDbService;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Тест сервиса метеоданных.
 *
 * @author Danila Fedortsov
 */
public class MeteoServiceTest {
    /**
     * Пробный сценарий работы с бд и метеосервисом.
     *
     * @throws IOException ошибка ввода
     */
    @Test
    public void usageTest() throws IOException {
        Path tempPath = Path.of("src\\test\\resources\\TemperaturesTest.xml"); // путь до данных о температуре
        Path presPath = Path.of("src\\test\\resources\\PressuresTest.xml");    // путь до данных о давлении

        // инициализация базы данных и инструментов работы с ней
        var propertyContainer = new PropertyContainer("test.properties"); // подгружаем свойства бд
        var provider = new DerbyDataSourceProvider(propertyContainer);            // подгружаем источник данных
        var meteoRepository = new DerbyMeteoRepository(provider.getDataSource()); // инициализируем репозиторий
        var meteoDbService = new MeteoDbService(meteoRepository);                 // инициализируем сервис бд
        meteoDbService.deleteAllMeteoRecords();                                   // удаляем старые записи

        var meteoService = new MeteoService(meteoDbService); // инициализируем класс главного сервиса

        var expectedDbRecords = meteoService.sendToDb(tempPath, presPath); // производим запись в бд
        var actualDbRecords = meteoDbService.getAllMeteoRecords(); // получаем все записи из бд

        Assertions.assertEquals( // проверяем соответствие длины списков
                expectedDbRecords.size(),
                actualDbRecords.size()
        );

//        expectedDbRecords = sortByMoment(expectedDbRecords);
//        actualDbRecords = sortByMoment(actualDbRecords);

        // поэлементно проверяем на соответствие элементов списка
        for (int i = 0; i < expectedDbRecords.size(); i++) {
            MeteoRecord expected = expectedDbRecords.get(i);
            MeteoRecord actual = actualDbRecords.get(i);

            Assertions.assertEquals(
                    expected.getId(),
                    actual.getId()
            );
            Assertions.assertEquals(
                    expected.getMoment(),
                    actual.getMoment()
            );
            Assertions.assertEquals(
                    expected.getTemperature(),
                    actual.getTemperature()
            );
            Assertions.assertEquals(
                    expected.getPressure(),
                    actual.getPressure()
            );
        }
    }

//    /**
//     * Сортирует список по времени.
//     *
//     * @param records список метеорологических записей
//     * @return отсортированный список метеорологических записей
//     */
//    public List<MeteoRecord> sortByMoment(List<MeteoRecord> records) {
//        return records.stream()
//                .sorted(Comparator.comparing(MeteoRecord::getMoment))
//                .collect(Collectors.toList());
//    }
}
