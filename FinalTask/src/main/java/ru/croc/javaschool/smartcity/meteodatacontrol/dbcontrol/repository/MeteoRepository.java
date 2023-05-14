package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.repository;

import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;

import java.util.UUID;

/**
 * Интерфейс репозитория задач для базы данных.
 *
 * @author Danila Fedortsov
 */
public interface MeteoRepository {
    /**
     * Метод инициализации таблицы.
     */
    void initTable();

    /**
     * Создание новой записи.
     *
     * @param meteoRecord метеорологическая запись
     * @return метеорологическая запись с идентификатором из базы данных, либо null если запись не удалась.
     */
    MeteoRecord create(MeteoRecord meteoRecord);

    /**
     * Поиск метеорологической записи по id.
     *
     * @param id идентификатор метеорологической записи
     * @return метеорологическая запись
     */
    MeteoRecord findById(UUID id);
}
