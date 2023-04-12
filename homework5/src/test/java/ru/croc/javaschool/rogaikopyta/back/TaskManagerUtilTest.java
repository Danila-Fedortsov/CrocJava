package ru.croc.javaschool.rogaikopyta.back;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * Тестирование {@link TaskManagerUtil}.
 *
 * @author Danila Fedortsov
 */
public class TaskManagerUtilTest {
    /**
     * Таск-менеджер.
     */
    TaskManagerUtil tmu;

    /**
     * Некая задача.
     */
    Task expectedTask;

    /**
     * Инициализирует таск-менеджер и некоторую задачу
     */
    @BeforeEach
    public void initTest() {
        this.tmu = new TaskManagerUtil("data.dat");
        tmu.clearTaskList();
        this.expectedTask = new Task(1, "A", "B", "C", "D");
    }

    /**
     * Тестирует запись и чтение из файла.
     */
    @Test
    public void getSetTasksTest() {
        tmu.clearTaskList();
        LinkedList<Task> expectedList = new LinkedList<>();
        Assertions.assertEquals(expectedList, tmu.getTasks());

        expectedList.add(expectedTask);
        tmu.setTasks(expectedList);
        Assertions.assertEquals(expectedList.toString(), tmu.getTasks().toString());
    }

    /**
     * Тестирует получение задачи по её коду.
     */
    @Test
    public void getTaskByIdTest() {
        tmu.clearTaskList();
        LinkedList<Task> expectedList = new LinkedList<>();
        expectedList.add(expectedTask);
        tmu.setTasks(expectedList);

        Assertions.assertEquals(expectedTask.toString(), tmu.getTaskById(1, tmu.getTasks()).toString());
    }

    /**
     * Тестирует добавление и удаление задачи.
     */
    @Test
    public void addDelTaskTest() {
        tmu.clearTaskList();
        tmu.addTask(expectedTask);
        Assertions.assertEquals(
                expectedTask.toString(),
                tmu.getTaskById(expectedTask.getId(), tmu.getTasks()).toString()
        );

        tmu.delTask(expectedTask.getId());
        Assertions.assertNull(tmu.getTaskById(expectedTask.getId(), tmu.getTasks()));
    }

    /**
     * Тестирует изменение названия и статуса задачи.
     */
    @Test
    public void setNameStatusTest() {
        tmu.clearTaskList();
        String expectedName = "Alex";
        String expectedStatus = "in progress";

        tmu.addTask(expectedTask);
        tmu.setTaskName(
                expectedTask.getId(),
                expectedName
        );
        tmu.setTaskStatus(
                expectedTask.getId(),
                expectedStatus
        );
        Task actualTask = tmu.getTaskById(expectedTask.getId(), tmu.getTasks());
        Assertions.assertEquals(
                expectedName,
                actualTask.getName()
        );
        Assertions.assertEquals(
                expectedStatus,
                actualTask.getStatus()
        );
    }
}
