package ru.croc.javaschool.smartcity.meteodatacontrol.back.dataprocessing;

import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.jaxb.XmlConverter;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.pressure.Pressure;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.pressure.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature.Temperature;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature.Temperatures;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Функции, преобразующие данные.
 *
 * @author Danila Fedortsov
 */
public class MeteoDataConverter {
    /**
     * Преобразует модель данных для xml-строк в модель для работы с базой даннных. Совмещает записи о давлении и
     * температуре в один список. В случае, если момент времени для давления и температуры совпал, делает смежную
     * запись. Если в каком-либо из списков присутствуют больше одного вхождения одного момента времени, записывается
     * последнее по порядку показание, как самое актуальное.
     *
     * @param tempData список данных о температуре
     * @param presData список данных о давлении
     * @return список метеорологических записей
     */
    public List<MeteoRecord> fromXmlToDbModel(Temperatures tempData, Pressures presData) {
        List<MeteoRecord> meteoRecords = new ArrayList<>();
        for (Temperature temp : tempData.getTemperatureList()) { // добавляем данные о температуре игнорируя давление.
            Optional<MeteoRecord> record = meteoRecords.stream()
                    .filter(rec -> rec
                            .getMoment()
                            .equals(Timestamp.valueOf(temp.getMoment())))
                    .findFirst(); // ищем вхождение такой же даты.

            if (record.isPresent()) { // если есть, то обновляем значение.
                record.get().setTemperature(temp.getValue());
            } else { // если нет то записываем новое.
                meteoRecords.add(new MeteoRecord(
                        Timestamp.valueOf(temp.getMoment()),
                        temp.getValue(),
                        null
                ));
            }
        }
        for (Pressure pres : presData.getPressureList()) {
            Optional<MeteoRecord> reqRec = meteoRecords.stream()
                    .filter(rec -> Timestamp
                            .valueOf(pres.getMoment())
                            .equals(rec.getMoment()))
                    .findFirst(); // ищем запись с такой же датой как у заданного давления.

            if (reqRec.isPresent()) { // если запись есть, то записываем давление в нее.
                reqRec.get().setPressure(pres.getValue());
            } else { // если нет, то создаём новую запись игнорируя температуру.
                meteoRecords.add(new MeteoRecord(
                        Timestamp.valueOf(pres.getMoment()),
                        null,
                        pres.getValue()
                ));
            }
        }

        return meteoRecords;
    }

    /**
     * Возвращает список температур из xml-файла.
     *
     * @param path путь до xml-файла температур
     * @return список температур
     */
    public Temperatures getTemperatures(Path path) {
        var xmlConverter = new XmlConverter();
        var temperatures = (Temperatures) null;

        try {
            temperatures = xmlConverter.fromXmlFile(path, Temperatures.class);
        } catch (IOException e) {
            System.out.println("Ошибка чтения xml-файла температуры. " + e.getMessage());
        }

        return temperatures;
    }

    /**
     * Возвращает список давлений из xml-файла.
     *
     * @param path путь до xml-файла давлений
     * @return список давлений
     */
    public Pressures getPressures(Path path) {
        var xmlConverter = new XmlConverter();
        var pressures = (Pressures) null;

        try {
            pressures = xmlConverter.fromXmlFile(path, Pressures.class);
        } catch (IOException e) {
            System.out.println("Ошибка чтения xml-файла давления. " + e.getMessage());
        }

        return pressures;
    }
}

