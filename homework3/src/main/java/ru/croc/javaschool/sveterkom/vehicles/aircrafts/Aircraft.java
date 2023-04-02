package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 *
 */
public abstract class Aircraft extends Vehicle {
    /**
     *
     */
    private double flightDuration;

    /**
     *
     * @param index
     */
    public Aircraft(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public double getFlightDuration() {
        return flightDuration;
    }

    /**
     *
     * @param flightDuration
     */
    public void setFlightDuration(double flightDuration) {
        this.flightDuration = flightDuration;
    }
}
