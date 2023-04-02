package ru.croc.javaschool.sveterkom.vehicles.motorcars;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 *
 */
public abstract class Motorcar extends Vehicle {
    /**
     *
     */
    private double enginePower;

    /**
     * @param index
     */
    public Motorcar(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public double getEnginePower() {
        return enginePower;
    }

    /**
     *
     * @param enginePower
     */
    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }
}
