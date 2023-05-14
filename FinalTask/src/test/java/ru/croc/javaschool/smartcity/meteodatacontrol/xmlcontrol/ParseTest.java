package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input.Pressure;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input.Pressures;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input.Temperature;
import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.patterns.input.Temperatures;

import java.io.IOException;
import java.util.List;

public class ParseTest {

    private final String tempPath = "src\\test\\resources\\TemperaturesTest.xml";


    @Test
    public void getMeasurementListFromFileTest() throws IOException {
        Temperatures temperatureList = Parse.getMeasurementListFromFile(
                tempPath,
                Temperatures.class
        );

        System.out.println(temperatureList.getTemperatureList().get(0));
    }
}
