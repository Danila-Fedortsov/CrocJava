package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

/**
 *
 */
public class ElectricScooter extends IMVehicle {
    /**
     *
     */
    private boolean isFolding;

    /**
     * @param index
     */
    public ElectricScooter(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public boolean isFolding() {
        return isFolding;
    }

    /**
     *
     * @param folding
     */
    public void setFolding(boolean folding) {
        isFolding = folding;
    }
}
