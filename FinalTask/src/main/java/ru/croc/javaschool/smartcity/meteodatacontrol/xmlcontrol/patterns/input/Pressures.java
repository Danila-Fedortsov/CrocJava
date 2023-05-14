package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Pressures")
public class Pressures {

    @XmlElement(name = "pressure")
    private List<Pressure> pressureList;

    public List<Pressure> getPressureList() {
        return pressureList;
    }
}
