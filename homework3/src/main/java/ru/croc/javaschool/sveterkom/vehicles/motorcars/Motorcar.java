package ru.croc.javaschool.sveterkom.vehicles.motorcars;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 * Автомобильный транспорт.
 *
 * @author Danila Fedortsov
 */
public abstract class Motorcar extends Vehicle {
    /**
     * Декоративное поле. <br>
     * Мощность двигателя.
     */
    private double enginePower;

    /**
     * Создаёт {@link Motorcar}.
     *
     * @param index номер автомобильного транспорта
     */
    public Motorcar(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю enginePower.
     *
     * @return мощность двигателя
     */
    public double getEnginePower() {
        return enginePower;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю enginePower.
     *
     * @param enginePower мощность двигателя
     */
    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }
}
