package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 *
 */
public abstract class IMVehicle extends Vehicle {
    /**
     * @param index
     * @param isBroken
     */
    public IMVehicle(int index, boolean isBroken) {
        super(index, isBroken);
    }

    /**
     * @param index
     */
    public IMVehicle(int index) {
        super(index);
    }
}
