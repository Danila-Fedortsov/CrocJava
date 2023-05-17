package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.repository.impl;

import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model.MeteoRecord;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.repository.MeteoRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при создании таблицы: " + e.getMessage());
        }
    }

    /**
     * Создание новой записи.
     *
     * @param meteoRecord метеорологическая запись
     * @return метеорологическая запись с идентификатором из базы данных, либо null если запись не удалась.
     */
    @Override
    public MeteoRecord create(MeteoRecord meteoRecord) {
        String query = String.format("INSERT INTO %s VALUES (?, ?, ?, ?)", MeteoRecord.TABLE_NAME);
        UUID entityId = UUID.randomUUID();

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query)) {

            statement.setString(1, entityId.toString());
            statement.setTimestamp(2, meteoRecord.getMoment());

            if (Objects.isNull(meteoRecord.getTemperature())) {
                statement.setNull(3, Types.DECIMAL);
            } else {
                statement.setString(3, meteoRecord.getTemperature().toString());
            }

            if (Objects.isNull(meteoRecord.getPressure())) {
                statement.setNull(4, Types.DECIMAL);
            } else {
                statement.setString(4, meteoRecord.getPressure().toString());
            }

            statement.execute();

        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (создание записи): " + e.getMessage());
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
                meteoRec = getMeteoRecord(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск): " + e.getMessage());
        }

        return meteoRec;
    }

    /**
     * Создание нескольких новых записей.
     *
     * @param meteoRecords список метеорологических записей
     * @return список метеорологических записей с идентификаторами из базы данных, либо пустой список если запись
     * не удалась
     */
    @Override
    public List<MeteoRecord> createAll(List<MeteoRecord> meteoRecords) {
        if (meteoRecords.isEmpty()) {
            return new ArrayList<>();
        }

        String query = String.format("INSERT INTO %s VALUES (?, ?, ?, ?)", MeteoRecord.TABLE_NAME);
        List<UUID> idList = new ArrayList<>(meteoRecords.size());
        for (int i = 0; i < meteoRecords.size(); i++) {
            idList.add(UUID.randomUUID());
        }

        try (var connection = dataSource.getConnection()) {
            for (int i = 0; i < meteoRecords.size(); i++) {
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, idList.get(i).toString());
                statement.setTimestamp(2, meteoRecords.get(i).getMoment());

                if (Objects.isNull(meteoRecords.get(i).getTemperature())) {
                    statement.setNull(3, Types.DECIMAL);
                } else {
                    statement.setString(3, meteoRecords.get(i).getTemperature().toString());
                }

                if (Objects.isNull(meteoRecords.get(i).getPressure())) {
                    statement.setNull(4, Types.DECIMAL);
                } else {
                    statement.setString(4, meteoRecords.get(i).getPressure().toString());
                }

                statement.execute();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (создание записи): " + e.getMessage());
        }

        return findAllById(idList);
    }

    /**
     * Поиск нескольких записей по списку идентификаторов.
     *
     * @param idList список идентификаторов метеорологических записей
     * @return список метеорологических записей из базы данных, либо пустой список если поиск не удался
     */
    @Override
    public List<MeteoRecord> findAllById(List<UUID> idList) {
        List<MeteoRecord> records = new ArrayList<>(idList.size());

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {

            for (UUID id : idList) {
                String query = String.format(
                        "SELECT id, moment, temperature, pressure FROM %s WHERE id = '%s'",
                        MeteoRecord.TABLE_NAME,
                        id.toString()
                );

                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    records.add(getMeteoRecord(resultSet));
                }
                resultSet.close();
            }

        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск): " + e.getMessage());
        }

        return records;
    }

    /**
     * Получение всех записей из таблицы. Нужно для тестов.
     *
     * @return список метеорологических записей
     */
    public List<MeteoRecord> getAll() {
        List<MeteoRecord> records = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", MeteoRecord.TABLE_NAME);

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                records.add(getMeteoRecord(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (получение всех записей): " + e.getMessage());
        }

        return records;
    }

    /**
     * Удаление всех записей из таблицы. Нужно для тестов.
     */
    @Override
    public void deleteAll() {
        String query = String.format("DELETE FROM %s", MeteoRecord.TABLE_NAME);

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (удаление всех записей): " + e.getMessage());
        }
    }

    /**
     * Возвращает результат запроса из множества результатов. Перед вызовом необходимо делать проверку на существование
     * результата.
     *
     * @param resultSet множество результатов
     * @return метеорологическая запись
     * @throws SQLException ошибка запроса
     */
    private MeteoRecord getMeteoRecord(ResultSet resultSet) throws SQLException {
        String temp = resultSet.getString("temperature");
        String pres = resultSet.getString("pressure");
        Double tempRes = Objects.isNull(temp) ? null : Double.parseDouble(temp);
        Double presRes = Objects.isNull(pres) ? null : Double.parseDouble(pres);

        return new MeteoRecord(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getTimestamp("moment"),
                tempRes,
                presRes
        );
    }
}
