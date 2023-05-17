package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.property;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Хранилище свойств.
 *
 * @author Danila Fedortsov
 */
public class PropertyContainer {
    /**
     * Словарь, в котором ключ - название свойства, а значение - значение данного свойства.
     */
    private final Map<String, String> properties = new HashMap<>();

    /**
     * Создаёт {@link PropertyContainer}. Загружает свойства из properties-файла в словарь {@link #properties}.
     *
     * @param fileName название файла с расширением properties
     * @throws IOException если файл не был найден в ресурсах или из-за проблем со считыванием
     */
    public PropertyContainer(String fileName) throws IOException {
        Properties appProperties = new Properties();
        appProperties.load(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(fileName)
        );

        for (var entry : appProperties.entrySet()) {
            this.properties.put(
                    (String) entry.getKey(),
                    (String) entry.getValue()
            );
        }
    }

    /**
     * Возвращает значение свойства по ключу.
     *
     * @param propertyKey название свойства
     * @return значение свойства
     */
    public String getProperty(String propertyKey) {
        return properties.getOrDefault(propertyKey, "");
    }
}
