package ru.croc.javaschool.smartcity.meteodatacontrol.dataprocessing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure.Pressure;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperature;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperatures;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MeteoDataConverterTest {

    @Test
    public void fromXmlToDbModelTest() {
        String moment = "2023-05-15 10:00:00";
        double temp = 30.0;
        double pres = 760.4;

        Temperatures temperatures = new Temperatures(List.of(
                new Temperature(temp, moment)
        ));
        Pressures pressures = new Pressures(List.of(
                new Pressure(pres, moment)
        ));
        List<MeteoRecord> meteoRecList = new ArrayList<>(List.of(
                new MeteoRecord(Timestamp.valueOf(moment), temp, pres)
        ));

        Assertions.assertEquals(
                meteoRecList.toString(),
                MeteoDataConverter
                        .fromXmlToDbModel(temperatures, pressures)
                        .toString()
        );
    }
}
