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
     * Дата и время в формате 'yyyy-mm-dd hh:mm:ss'.
     */
    @XmlElement
    private String moment;

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
     * @return дата и время в формате 'yyyy-mm-dd hh:mm:ss'
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
