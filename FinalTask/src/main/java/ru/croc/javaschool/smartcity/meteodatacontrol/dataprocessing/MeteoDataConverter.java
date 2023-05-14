package ru.croc.javaschool.smartcity.meteodatacontrol.dataprocessing;

import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature.Temperature;

import java.util.ArrayList;
import java.util.List;

public class MeteoDataConverter {

    public static List<MeteoRecord> fromXmlToDbModel(Temperature tempData, Pressures presData) {
        List<MeteoRecord> meteoRecords = new ArrayList<>();
        return meteoRecords;
    }
}
