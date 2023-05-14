package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input;

import javax.xml.bind.annotation.XmlElement;

public class Temperature {
    @XmlElement
    private double value;

    @XmlElement
    private String moment;

    public double getValue() {
        return value;
    }

    public String getMoment() {
        return moment;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                ", moment='" + moment + '\'' +
                '}';
    }
}
