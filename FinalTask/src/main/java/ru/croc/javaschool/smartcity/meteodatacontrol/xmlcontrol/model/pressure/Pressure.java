package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.pressure;

import javax.xml.bind.annotation.XmlElement;

/**
 * Атмосферное давление.
 *
 * @author Danila Fedortsov
 */
public class Pressure {
    /**
     * Значение (мм рт. ст.).
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
     * @return давление (мм рт. ст.)
     */
    public double getValue() {
        return value;
    }

    /**
     * Возвращает дату и время.
     *
     * @return дата и время в формате 'yyyy-mm-dd hh:mm:ss'
     */
    public String getMoment() {
        return moment;
    }

    /**
     * Возвращает строковое представление объекта класса {@link Pressure}. Нужно для тестов.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Pressure{" +
                "value=" + value +
                ", moment='" + moment + '\'' +
                '}';
    }
}
