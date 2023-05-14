package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.model.temperature;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Optional;

/**
 * Список показаний о температуре воздуха.
 *
 * @author Danila Fedortsov
 */
@XmlRootElement(name = "Temperatures")
public class Temperatures {
    /**
     * Список.
     */
    @XmlElement(name = "temperature")
    private List<Temperature> temperatureList;

    /**
     * Возвращает список.
     *
     * @return список
     */
    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    /**
     * Возвращает показание температуры по заданной дате.
     *
     * @param moment дата и время
     * @return показание температуры или null если не было найдено.
     */
    public Temperature getTemperature(String moment) {
        return temperatureList.stream()
                .filter(m -> m.getMoment().equals(moment))
                .findFirst()
                .orElse(null);
    }
}