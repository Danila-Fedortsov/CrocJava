package ru.croc.javaschool.smartcity.meteodatacontrol.back.xmlcontrol.model.pressure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Список показаний об атмосферном давлении.
 *
 * @author Danila Fedortsov
 */
@XmlRootElement(name = "Pressures")
public class Pressures {
    /**
     * Список.
     */
    @XmlElement(name = "pressure")
    private List<Pressure> pressureList;

    /**
     * Создаёт {@link Pressures}. Нужен для конвертации из xml.
     */
    public Pressures() {
    }

    /**
     * Создаёт {@link Pressures}. Нужен для тестов.
     *
     * @param pressureList список давлений
     */
    public Pressures(List<Pressure> pressureList) {
        this.pressureList = pressureList;
    }

    /**
     * Возвращает список.
     *
     * @return список
     */
    public List<Pressure> getPressureList() {
        if (Objects.isNull(pressureList)) {
            return new ArrayList<>();
        }
        return pressureList;
    }

}
