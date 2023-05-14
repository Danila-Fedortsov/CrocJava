package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol;

import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb.XmlConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parse {

    public static <T> T getMeasurementListFromFile(String path, Class<T> type) throws IOException {
        String xmlString = Files.readString(
                Path.of(path)
        );
        XmlConverter xmlConverter = new XmlConverter();

        return xmlConverter.fromXml(
                xmlString,
                type
        );
    }
}
