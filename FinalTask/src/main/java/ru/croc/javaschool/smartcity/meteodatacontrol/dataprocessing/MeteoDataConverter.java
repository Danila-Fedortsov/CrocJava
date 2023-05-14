package ru.croc.javaschool.smartcity.meteodatacontrol.dataprocessing;

import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure.Pressure;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperature;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperatures;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Функции, преобразующие данные из одной модели в другую.
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
    public static List<MeteoRecord> fromXmlToDbModel(Temperatures tempData, Pressures presData) {
        List<MeteoRecord> meteoRecords = new ArrayList<>();
        for (Temperature temp : tempData.getTemperatureList()) { // добавляем данные о температуре игнорируя давление.
            meteoRecords.add(new MeteoRecord(
                    Timestamp.valueOf(temp.getMoment()),
                    temp.getValue(),
                    null
            ));
        }
        for (Pressure pres : presData.getPressureList()) {
            Optional<MeteoRecord> reqRec = meteoRecords.stream()
                    .filter(rec -> Timestamp
                            .valueOf(pres.getMoment())
                            .equals(rec.getMoment())
                    ).findFirst(); // ищем запись с такой же датой как у заданного давления.

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
}

