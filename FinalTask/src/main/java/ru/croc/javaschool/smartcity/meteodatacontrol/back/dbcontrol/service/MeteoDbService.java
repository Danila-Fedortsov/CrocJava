package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.service;

import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.repository.MeteoRepository;

import java.util.List;

/**
 * Сервис пользовательских запросов к базе данных.
 *
 * @author Danila Fedortsov
 */
public class MeteoDbService {
    /**
     * Репозиторий задач.
     */
    private final MeteoRepository repository;

    /**
     * Создаёт {@link MeteoDbService}.
     *
     * @param repository репозиторий задач
     */
    public MeteoDbService(MeteoRepository repository) {
        this.repository = repository;
    }

    /**
     * Создание новых метеорологических записей.
     *
     * @param records список метеорологических записей
     * @return список метеорологических записей из базы данных
     */
    public List<MeteoRecord> createAllNewMeteoRecord(List<MeteoRecord> records) {
        return repository.createAll(records);
    }

    /**
     * Возвращает все метеорологические записи.
     *
     * @return список метеорологических записей
     */
    public List<MeteoRecord> getAllMeteoRecords() {
        return repository.getAll();
    }

    /**
     * Удаляет все метеорологические записи.
     */
    public void deleteAllMeteoRecords() {
        repository.deleteAll();
    }
}
