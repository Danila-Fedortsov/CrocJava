package ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.temperature;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * Создаёт {@link Temperatures}. Нужен для конвертации из xml.
     */
    public Temperatures() {
    }

    /**
     * Создаёт {@link Temperatures}. Нужен для тестов.
     *
     * @param temperatureList список температур
     */
    public Temperatures(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

    /**
     * Возвращает список.
     *
     * @return список
     */
    public List<Temperature> getTemperatureList() {
        if (Objects.isNull(temperatureList)){
            return new ArrayList<>();
        }
        return temperatureList;
    }
}