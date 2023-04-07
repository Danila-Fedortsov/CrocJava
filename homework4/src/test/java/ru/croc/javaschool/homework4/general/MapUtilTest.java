package ru.croc.javaschool.homework4.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
=======

>>>>>>> master/master
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Тестирование {@link MapUtil}.
 *
 * @author Danila Fedortsov
 */
public class MapUtilTest {
    /**
     * {@link MapUtil#sortByValue} сортирует по убыванию ключа. Для сравнения используем {@link #toString}.
     * В качетсве передаваемого значения используем строку, условно полученную методом {@link #toString} применённым к
     * отсортированному словарю.
     */
    @Test
    public void sortByValueTest() {
        String expectedStr = "{C=3, B=1, A=-1}";
        Map<String, Integer> testMap = new HashMap<>(Map.ofEntries(
                entry("B", 1),
                entry("A", -1),
                entry("C", 3)
        ));

        Assertions.assertEquals(expectedStr, MapUtil.sortByValue(testMap).toString());
    }
}
