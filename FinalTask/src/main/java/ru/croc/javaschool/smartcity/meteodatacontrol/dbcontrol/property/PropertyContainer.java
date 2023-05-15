package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.property;

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
    private static final Map<String, String> properties = new HashMap<>();

    /**
     * Загружает свойства из properties-файла в словарь {@link #properties}.
     *
     * @param fileName название файла с расширением properties
     * @throws IOException если файл не был найден в ресурсах или из-за проблем с считыванием
     */
    public static void loadProperties(String fileName) throws IOException {
        Properties appProperties = new Properties();
        try {
            appProperties.load(
                    Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(fileName)
            );

            for (var entry : appProperties.entrySet()) {
                properties.put(
                        (String) entry.getKey(),
                        (String) entry.getValue()
                );
            }
        } catch (Exception e) {
            System.out.println("Возникла ошибка при загрузке настроек.");
            throw e;
        }
    }

    /**
     * Возвращает значение свойства по ключу.
     *
     * @param propertyKey название свойства
     * @return значение свойства
     */
    public static String getProperty(String propertyKey) {
        return properties.getOrDefault(propertyKey, "");
    }
}
