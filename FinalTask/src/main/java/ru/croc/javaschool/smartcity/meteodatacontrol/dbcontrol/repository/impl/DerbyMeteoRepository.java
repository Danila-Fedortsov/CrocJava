package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.repository.impl;

import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.repository.MeteoRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.UUID;

/**
 * Реализация репозитория задач для Derby.
 *
 * @author Danila Fedortsov
 */
public class DerbyMeteoRepository implements MeteoRepository {
    /**
     * Источник данных.
     */
    private final DataSource dataSource;

    /**
     * Создаёт {@link DerbyMeteoRepository}.
     *
     * @param dataSource источник данных
     */
    public DerbyMeteoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Инициализация таблицы.
     */
    @Override
    public void initTable() {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            ResultSet resultSet = connection.getMetaData().getTables(
                    null,
                    null,
                    MeteoRecord.TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"}
            );

            if (!resultSet.next()) {
                String createTable = String.format("CREATE TABLE %s (%s, %s, %s, %s)",
                        MeteoRecord.TABLE_NAME,
                        "id VARCHAR(36) PRIMARY KEY",
                        "moment TIMESTAMP",
                        "temperature DECIMAL(4, 1)",
                        "pressure DECIMAL(4, 1)"
                );
                statement.executeUpdate(createTable);
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при создании таблицы: " + e.getMessage());
        }
    }

    /**
     * Создание новой записи.
     *
     * @param meteoRec метеорологическая запись
     * @return метеорологическая запись с идентификатором из базы данных, либо null если запись не удалась.
     */
    @Override
    public MeteoRecord create(MeteoRecord meteoRec) {
        String query = String.format("INSERT INTO %s VALUES (?, ?, ?, ?)", MeteoRecord.TABLE_NAME);
        UUID entityId = UUID.randomUUID();

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setString(1, entityId.toString());
            statement.setTimestamp(2, meteoRec.getMoment());

            if (Objects.isNull(meteoRec.getTemperature())) {
                statement.setNull(3, Types.DECIMAL);
            } else {
                statement.setString(3, meteoRec.getTemperature().toString());
            }

            if (Objects.isNull(meteoRec.getPressure())) {
                statement.setNull(4, Types.DECIMAL);
            } else {
                statement.setString(4, meteoRec.getPressure().toString());
            }

            statement.execute();

        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (создание): " + e.getMessage());
            return null;
        }

        return findById(entityId);
    }

    /**
     * Поиск записи по идентификатору.
     *
     * @param id идентификатор метеорологической записи
     * @return метеорологическая запись из базы данных, либо null если поиск не удался
     */
    @Override
    public MeteoRecord findById(UUID id) {
        MeteoRecord meteoRec = null;
        String query = String.format(
                "SELECT id, moment, temperature, pressure FROM %s WHERE id = '%s'",
                MeteoRecord.TABLE_NAME,
                id
        );

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                String temp = resultSet.getString("temperature");
                String pres = resultSet.getString("pressure");
                Double tempRes = Objects.isNull(temp) ? null : Double.parseDouble(temp);
                Double presRes = Objects.isNull(pres) ? null : Double.parseDouble(pres);

                meteoRec = new MeteoRecord(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getTimestamp("moment"),
                        tempRes,
                        presRes
                );
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск): " + e.getMessage());
        }

        return meteoRec;
    }
}
