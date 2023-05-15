package ru.croc.javaschool.smartcity.meteodatacontrol;

import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.dataprocessing.MeteoDataConverter;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.dsprovider.impl.DerbyDataSourceProvider;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.property.PropertyContainer;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.repository.impl.DerbyMeteoRepository;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.service.MeteoService;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb.XmlConverter;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperatures;

import java.io.IOException;
import java.util.List;

/**
 * Тест управления метеоданными.
 *
 * @author Danila Fedortsov
 */
public class MeteoDataControlTest {

    /**
     * Пробный сценарий работы с модулями.
     *
     * @throws IOException ошибка ввода
     */
    @Test
    public void usageTest() throws IOException {
        PropertyContainer.loadProperties("test.properties"); // подгружаем свойства

        String tempPath = "src\\test\\resources\\TemperaturesTest.xml"; // путь до данных о температуре
        String presPath = "src\\test\\resources\\PressuresTest.xml"; // путь до данных о давлении
        XmlConverter xmlConverter = new XmlConverter();

        // инициализация базы данных и инструментов работы с ней
        var provider = new DerbyDataSourceProvider();
        var meteoRepository = new DerbyMeteoRepository(provider.getDataSource());
        var meteoService = new MeteoService(meteoRepository);

        // парсинг xml-файлов и обработка для последующей записи в бд
        Temperatures temperatures = xmlConverter.fromXmlFile(tempPath, Temperatures.class);
        Pressures pressures = xmlConverter.fromXmlFile(presPath, Pressures.class);
        List<MeteoRecord> meteoRecords = MeteoDataConverter.fromXmlToDbModel(temperatures, pressures);

        // запись в бд
        List<MeteoRecord> dbMeteoRecords = meteoService.createAllNewMeteoRecord(meteoRecords);

        for (var temp : temperatures.getTemperatureList()) { // данные из xml-файла с температурой
            System.out.println(temp.getMoment() + " " + temp.getValue());
        }
        System.out.println();
        for (var pres : pressures.getPressureList()) {  // данные из xml-файла с давлением
            System.out.println(pres.getMoment() + " " + pres.getValue());
        }
        System.out.println();
        for (var rec : meteoRecords) {  // смежные записи для последющей записи в бд
            System.out.println(rec.getMoment() + " " + rec.getTemperature() + " " + rec.getPressure());
        }
        System.out.println();
        for (var rec : dbMeteoRecords) { // данные, которые вернула бд
            System.out.println(rec.getId() + " " + rec.getMoment() + " " + rec.getTemperature() + " " + rec.getPressure());
        }
    }
}
