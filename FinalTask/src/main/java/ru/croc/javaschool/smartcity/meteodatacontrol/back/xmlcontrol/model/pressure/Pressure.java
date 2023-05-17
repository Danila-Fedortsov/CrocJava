package ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.pressure;

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
     * Дата и время в формате 'yyyy-mm-dd hh:mm:ss.nnnnnn'.
     */
    @XmlElement
    private String moment;

    /**
     * Создаёт {@link Pressure}. Нужен для конвертации из xml.
     */
    public Pressure() {
    }

    /**
     * Создаёт {@link Pressure}. Нужен для тестов.
     *
     * @param value  значение давления
     * @param moment дата и время измерения в формате 'yyyy-mm-dd hh:mm:ss.nnnnnn'
     */
    public Pressure(double value, String moment) {
        this.value = value;
        this.moment = moment;
    }

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
     * @return дата и время в формате 'yyyy-mm-dd hh:mm:ss.nnnnnn'
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
