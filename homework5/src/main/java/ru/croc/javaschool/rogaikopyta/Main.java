package ru.croc.javaschool.rogaikopyta;

import ru.croc.javaschool.rogaikopyta.front.TerminalGUI;

/**
 * Главный класс.
 *
 * @author Danila Fedortsov
 */
public class Main {
    /**
     * Точка входа в программу. Запускает обработчик ввода в терминал.
     *
     * @param args args
     */
    public static void main(String[] args) {
        TerminalGUI terminal = new TerminalGUI();
        terminal.handler();
    }
}
