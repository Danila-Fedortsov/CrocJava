package ru.croc.javaschool.homework4.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Тестирование {@link WorkingWithTexts}.
 *
 * @author Danila Fedortsov
 */
public class WorkingWithTextsTest {
    /**
     * Тестирование метода {@link WorkingWithTexts#getRatingMap}.
     */
    @Test
    public void getRatingMapTest() {
        String expectedStr = "{Саша=50.0, Кеша=37.5}";
        HashMap<String, Double> actualArticles = WorkingWithTexts.getRatingMap(
                new ArrayList<>(List.of(
                        "Я ;Кеша;Я, че. Нет, я! Нет, ага? Да, точно",
                        "ИиИИ аа;Саша;Ииии, зачем всё это, аа? Ииии.",
                        "Мы идём;Кеша;Мы стоим, нет идём! Всмысле мы?."
                ))
        );
        Assertions.assertNotNull(actualArticles);
        Assertions.assertEquals(expectedStr, actualArticles.toString());
    }
}
