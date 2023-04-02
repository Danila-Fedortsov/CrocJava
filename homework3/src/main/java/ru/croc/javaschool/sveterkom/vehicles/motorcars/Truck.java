package ru.croc.javaschool.sveterkom.vehicles.motorcars;

/**
 *
 */
public class Truck extends Motorcar {
    /**
     *
     */
    private double loadCapacity;

    /**
     * @param index
     */
    public Truck(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public double getLoadCapacity() {
        return loadCapacity;
    }

    /**
     *
     * @param loadCapacity
     */
    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
