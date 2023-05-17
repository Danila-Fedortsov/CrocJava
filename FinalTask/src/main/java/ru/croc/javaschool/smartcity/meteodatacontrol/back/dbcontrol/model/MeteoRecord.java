package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.model;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Запись о погодных условиях.
 *
 * @author Danila Fedortsov
 */
public class MeteoRecord {
    /**
     * Название таблицы в которой хранятся записи.
     */
    public static final String TABLE_NAME = "meteo";

    /**
     * Идентификатор записи.
     */
    private UUID id;

    /**
     * Дата и время.
     */
    private Timestamp moment;

    /**
     * Температура воздуха.
     */
    private Double temperature;

    /**
     * Атмосферное давление.
     */
    private Double pressure;

    /**
     * Создаёт {@link MeteoRecord}.
     *
     * @param id          идентификатор
     * @param moment      дата и время в формате
     * @param temperature температура воздуха
     * @param pressure    атмосферное давление
     */
    public MeteoRecord(UUID id, Timestamp moment, Double temperature, Double pressure) {
        this.id = id;
        this.moment = moment;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    /**
     * Создаёт {@link MeteoRecord}.
     *
     * @param moment      дата и время в формате
     * @param temperature температура воздуха
     * @param pressure    атмосферное давление
     */
    public MeteoRecord(Timestamp moment, Double temperature, Double pressure) {
        this(null, moment, temperature, pressure);
    }

    /**
     * Возвращает идентификатор.
     *
     * @return уникальный номер
     */
    public UUID getId() {
        return id;
    }

    /**
     * Возвращает дату и время .
     *
     * @return дата и время
     */
    public Timestamp getMoment() {
        return moment;
    }

    /**
     * Задаёт новое значение температуры.
     *
     * @param temperature температура воздуха
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Возвращает температуру воздуха.
     *
     * @return температура воздуха
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * Задаёт новое значение давления.
     *
     * @param pressure атмосферное давление
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * Возвращает атмосферное давление.
     *
     * @return атмосферное давление
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * Строковое представление метеорологической записи. Нужен для тестов.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "MeteoRecord{" +
                "id=" + id +
                ", moment=" + moment +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                '}';
    }
}
