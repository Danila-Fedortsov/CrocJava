package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol;


import ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb.XmlConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Parse {

    public static <T> T getMeasurementListFromFile(String path, Class<T> type) throws IOException {
        String xmlString = getXmlString(path);
        XmlConverter xmlConverter = new XmlConverter();
        return xmlConverter.fromXml(xmlString, type);
    }

    private static String getXmlString(String path) throws IOException {
        return Files.readString(
                Path.of(path)
        );
    }
}
