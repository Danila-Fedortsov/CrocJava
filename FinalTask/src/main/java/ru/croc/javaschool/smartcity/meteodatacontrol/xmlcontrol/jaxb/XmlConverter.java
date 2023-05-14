package ru.croc.javaschool.smartcity.meteodatacontrol.xmlcontrol.jaxb;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Преобразователь xml-строк и объектов.
 *
 * @author Danila Fedortsov
 */
public class XmlConverter {
    /**
     * Объект-преобразователь.
     */
    private XmlMapper xmlMapper;

    /**
     * Преобразует xml-строку в объект.
     *
     * @param xml  xml-строка
     * @param type класс возвращаемого объекта
     * @param <T>  тип возвращаемого объекта
     * @return объект, соответствующий xml-модели
     * @throws IOException ошибка интерпретации xml-строки
     */
    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        return getMapper().readValue(xml, type);
    }

    /**
     * Преобразует строку из xml-файла в объект.
     *
     * @param path полный или относительный путь до объекта
     * @param type класс возвращаемого объекта
     * @param <T>  тип возвращаемого объекта
     * @return объект соответствующий xml-модели
     * @throws IOException ошибка интерпретации xml-строки или ошибка чтения файла
     */
    public <T> T fromXmlFile(String path, Class<T> type) throws IOException {
        return fromXml(
                Files.readString(Path.of(path)),
                type
        );
    }

    /**
     * Возвращает преобразователь xml-строк.
     *
     * @return преобразователь
     */
    private XmlMapper getMapper() {
        if (xmlMapper == null) {
            this.xmlMapper = new XmlMapper();
            this.xmlMapper.setDefaultUseWrapper(false);
            this.xmlMapper.registerModule(new JaxbAnnotationModule());
        }
        return xmlMapper;
    }
}
