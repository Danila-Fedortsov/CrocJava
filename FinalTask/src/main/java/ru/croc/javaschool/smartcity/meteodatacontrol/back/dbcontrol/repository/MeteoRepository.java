package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.repository;

import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;

import java.util.List;
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
     * @return метеорологическая запись с идентификатором из базы данных, либо null если запись не удалась
     */
    MeteoRecord create(MeteoRecord meteoRecord);

    /**
     * Поиск метеорологической записи по id.
     *
     * @param id идентификатор метеорологической записи
     * @return метеорологическая запись
     */
    MeteoRecord findById(UUID id);

    /**
     * Создание нескольких новых записей.
     *
     * @param meteoRecords список метеорологических записей
     * @return список метеорологических записей с идентификаторами из базы данных, либо null если запись не удалась
     */
    List<MeteoRecord> createAll(List<MeteoRecord> meteoRecords);

    /**
     * Поиск метеорологических записей по списку id.
     *
     * @param idList список идентификаторов метеорологических записей
     * @return список метеорологических записей
     */
    List<MeteoRecord> findAllById(List<UUID> idList);

    /**
     * Удаление всех записей. Нужно для тестов.
     */
    void deleteAll();

    /**
     * Получение всех метеорологических записей из базы данных.
     *
     * @return список метеорологических записей
     */
    List<MeteoRecord> getAll();
}
