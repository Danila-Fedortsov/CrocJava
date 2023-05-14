package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.service;

import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.repository.MeteoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис пользовательских запросов к базе данных.
 */
public class MeteoService {
    /**
     * Репозиторий задач.
     */
    private final MeteoRepository repository;

    /**
     * Создаёт {@link MeteoService}.
     *
     * @param repository репозиторий задач
     */
    public MeteoService(MeteoRepository repository) {
        this.repository = repository;
    }

    /**
     * Создание новых метеорологических записей.
     *
     * @param records список метеорологических записей
     * @return список метеорологических записей из базы данных
     */
    public List<MeteoRecord> createAllNewMeteoRecord(List<MeteoRecord> records) {
        List<MeteoRecord> dbRecords = new ArrayList<>(records.size());
        for (MeteoRecord rec : records) {
            dbRecords.add(repository.create(rec));
        }
        return dbRecords;
    }
}
