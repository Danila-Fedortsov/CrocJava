package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Temperatures")
public class Temperatures {

    @XmlElement(name = "temperature")
    private List<Temperature> temperatureList;

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }
}