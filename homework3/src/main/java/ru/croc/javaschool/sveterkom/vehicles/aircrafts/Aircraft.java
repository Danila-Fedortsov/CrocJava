package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 *
 */
public abstract class Aircraft extends Vehicle {
    /**
     * @param index
     * @param isBroken
     */
    public Aircraft(int index, boolean isBroken) {
        super(index, isBroken);
    }

    /**
     *
     * @param index
     */
    public Aircraft(int index) {
        super(index);
    }
}
