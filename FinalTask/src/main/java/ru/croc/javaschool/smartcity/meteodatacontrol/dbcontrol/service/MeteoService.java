package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.service;

import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.repository.MeteoRepository;

import java.sql.Timestamp;

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
     * Создание новой метеорологической записи.
     *
     * @param moment дата и время в формате 'yyyy-mm-dd hh:mm:ss'
     * @param temperature температура воздуха (°C)
     * @param pressure атмосферное давление (мм рт. ст.)
     * @return метеорологическая запись из базы данных, либо null если запись не удалась
     */
    public MeteoRecord createNewMeteoRecord(Timestamp moment, double temperature, double pressure) {
        return repository.create(
                new MeteoRecord(
                        moment,
                        temperature,
                        pressure
                ));
    }
}
