package ru.croc.javaschool.smartcity.meteodatacontrol.service;

import ru.croc.javaschool.smartcity.meteodatacontrol.back.dataprocessing.MeteoDataConverter;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.service.MeteoDbService;

import java.nio.file.Path;
import java.util.List;

/**
 * Сервис для работы с метеоданными.
 *
 * @author Danila Fedortsov
 */
public class MeteoService {
    /**
     * Сервис для работы с базой данных.
     */
    MeteoDbService dbService;

    /**
     * Создаёт {@link MeteoService}.
     *
     * @param dbService сервис для работы с базой данных
     */
    public MeteoService(MeteoDbService dbService) {
        this.dbService = dbService;
    }

    /**
     * Метод отправки данных о температуре и давлении из файлов в базу данных.
     *
     * @param tempPath путь до xml-файла с данными о температуре
     * @param presPath путь до xml-файла с данными о давлении
     * @return записи, сделанные в базе данных
     */
    public List<MeteoRecord> sendToDb(Path tempPath, Path presPath) {
        var converter = new MeteoDataConverter();
        var records = converter.fromXmlToDbModel(
                converter.getTemperatures(tempPath),
                converter.getPressures(presPath)
        );

        return dbService.createAllNewMeteoRecord(records);
    }
}
