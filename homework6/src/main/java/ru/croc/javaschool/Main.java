package ru.croc.javaschool;

import ru.croc.javaschool.peopleandprojects.DataConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Сценарий.
 *
 * @author Danila Fedortsov
 */
public class Main {
    /**
     * Точка входа в программу.
     *
     * @param args args
     * @throws IOException бывает конечно
     */
    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get("src/main/resources", "projects.xml");
        String outputPath = "src/main/resources/people.xml";
        DataConverter dataConverter = new DataConverter();

        String xml = Files.readString(inputPath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
        writer.write(dataConverter.projectsToPeopleClearXml(xml));
        writer.close();
    }
}