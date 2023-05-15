package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature;

import javax.xml.bind.annotation.XmlElement;

/**
 * Температура воздуха.
 *
 * @author Danila Fedortsov
 */
public class Temperature {
    /**
     * Значение (°C).
     */
    @XmlElement
    private double value;

    /**
     * Дата и время в формате 'yyyy-mm-dd hh:mm:ss.nnnnnn'.
     */
    @XmlElement
    private String moment;

    /**
     * Создаёт {@link Temperature}. Нужен для конвертации из xml.
     */
    public Temperature() {
    }

    /**
     * Создаёт {@link Temperature}. Нужен для тестов.
     *
     * @param value  значение температуры
     * @param moment дата и время измерения в формате 'yyyy-mm-dd hh:mm:ss.nnnnnn'
     */
    public Temperature(double value, String moment) {
        this.value = value;
        this.moment = moment;
    }

    /**
     * Возвращает значение.
     *
     * @return температура (°C)
     */
    public double getValue() {
        return value;
    }

    /**
     * Возвращет дату и время.
     *
     * @return дата и время в формате 'yyyy-mm-dd hh:mm:ss.nnnnnn'
     */
    public String getMoment() {
        return moment;
    }

    /**
     * Возвращает строковое представление объекта класса {@link Temperature}. Нужно для тестов.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                ", moment='" + moment + '\'' +
                '}';
    }
}
