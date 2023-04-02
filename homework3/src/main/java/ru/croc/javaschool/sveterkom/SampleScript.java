package ru.croc.javaschool.sveterkom;

import java.time.LocalDate;

import ru.croc.javaschool.sveterkom.rent.RentManagement;
import ru.croc.javaschool.sveterkom.vehicles.aircrafts.*;
import ru.croc.javaschool.sveterkom.vehicles.IMVehicles.*;
import ru.croc.javaschool.sveterkom.vehicles.motorcars.*;

/**
 * Пример сценария работы с {@link RentManagement}.
 *
 * @author Danila Fedortsov
 */
public class SampleScript {
    /**
     * Точка входа в программу.
     *
     * @param args args
     */
    public static void main(String[] args) {
        // Инициализировали управление арендой.
        RentManagement rm = new RentManagement();

        // Добавили 10 единиц разного транспорта.
        rm.addVehicle(new Car(0));
        rm.addVehicle(new Truck(0));
        rm.addVehicle(new Car(0));
        rm.addVehicle(new Helicopter(0));
        rm.addVehicle(new BusinessJet(0));
        rm.addVehicle(new BusinessJet(0));
        rm.addVehicle(new GyroScooter(0));
        rm.addVehicle(new GyroScooter(0));
        rm.addVehicle(new GyroScooter(0));
        rm.addVehicle(new Truck(0));
        rm.addVehicle(new Truck(0));

        // Допустим грузовик с индексом 9 неисправен и нам надо его списать.
        if (!rm.decommissioningVehicle(9)) {
            System.out.println("Транспорта с таким номером нет! Повторите попытку.\n");
        } else {
            System.out.println("Транспорт с индексом " + 9 + " списан.\n");
        }

        // Кто-то захотел арендовать авиа транспорт на заданные даты.
        // Проверим свободные ТС.
        System.out.println(rm.printFreeCategories(
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30)
        ));

        // Есть 3 свободных воздушных судна в заданные даты.
        // Допустим клиент захотел арендовать ВТС 5.
        if (!rm.rent(5,
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30))) {
            System.out.println("Данного транспорта не существует, или он находится в аренде.\n");
        } else {
            System.out.println("Транспорт с номером " + 5 + " успешно арендован на заданные даты.\n");
        }

        // Проверим список свободных ТС на заданные даты.
        System.out.println(rm.printFreeCategories(
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30)
        ));

        // Попробуем арендовать ТС с несуществующим номером.
        if (!rm.rent(90,
                LocalDate.of(2023, 3, 29),
                LocalDate.of(2023, 4, 11))) {
            System.out.println("Данного транспорта не существует, или он находится в аренде.\n");
        } else {
            System.out.println("Транспорт с номером " + 90 + " успешно арендован на заданные даты.\n");
        }

        // Или уже арендованный транспорт.
        if (!rm.rent(5,
                LocalDate.of(2023, 3, 29),
                LocalDate.of(2023, 4, 11))) {
            System.out.println("Данного транспорта не существует, или он находится в аренде.\n");
        } else {
            System.out.println("Транспорт с номером " + 5 + " успешно арендован на заданные даты.\n");
        }

        // Арендуем еще парочку.
        if (!rm.rent(1,
                LocalDate.of(2023, 1, 21),
                LocalDate.of(2023, 2, 4))) {
            System.out.println("Данного транспорта не существует, или он находится в аренде.\n");
        } else {
            System.out.println("Транспорт с номером " + 1 + " успешно арендован на заданные даты.\n");
        }

        if (!rm.rent(2,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 10, 24))) {
            System.out.println("Данного транспорта не существует, или он находится в аренде.\n");
        } else {
            System.out.println("Транспорт с номером " + 2 + " успешно арендован на заданные даты.\n");
        }

        // Выведем статистику для начальства на заданную дату.
        System.out.println(
                rm.printReport(LocalDate.of(2023, 3, 31))
        );
    }
}