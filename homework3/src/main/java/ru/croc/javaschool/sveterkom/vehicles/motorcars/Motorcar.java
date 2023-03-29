package ru.croc.javaschool.sveterkom.vehicles.motorcars;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 *
 */
public abstract class Motorcar extends Vehicle {
    /**
     * @param index
     * @param isBroken
     */
    public Motorcar(int index, boolean isBroken) {
        super(index, isBroken);
    }

    /**
     * @param index
     */
    public Motorcar(int index) {
        super(index);
    }
}
