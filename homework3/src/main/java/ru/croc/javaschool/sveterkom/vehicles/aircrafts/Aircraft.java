package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 * Воздушное судно.
 *
 * @author Danila Fedortsov
 */
public abstract class Aircraft extends Vehicle {
    /**
     * Декоративное поле. <br>
     * Продолжительность полёта в часах.
     */
    private double flightDuration;

    /**
     * Создаёт {@link Aircraft}.
     *
     * @param index номер воздушного судна.
     */
    public Aircraft(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю flightDuration.
     *
     * @return длительность полёта в часах
     */
    public double getFlightDuration() {
        return flightDuration;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю flightDuration.
     *
     * @param flightDuration новая длительность полёта в часах
     */
    public void setFlightDuration(double flightDuration) {
        this.flightDuration = flightDuration;
    }
}
