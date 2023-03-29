package ru.croc.javaschool.sveterkom;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println(LocalDate.of(2020, 1, 1).isBefore(LocalDate.of(2020, 1, 2)));
    }
}