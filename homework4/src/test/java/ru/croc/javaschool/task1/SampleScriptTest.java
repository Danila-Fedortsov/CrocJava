package ru.croc.javaschool.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тестирование класса {@link SampleScript}.
 */
public class SampleScriptTest {
    /**
     * Тестирование {@link SampleScript#inc()}.
     */
    @Test
    public void incTest() {
        int expectedRes = 1;
        Assertions.assertEquals(expectedRes, SampleScript.inc());
    }
}
