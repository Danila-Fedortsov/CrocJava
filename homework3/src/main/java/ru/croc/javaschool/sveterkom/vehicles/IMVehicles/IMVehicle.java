package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 *
 */
public abstract class IMVehicle extends Vehicle {
    /**
     *
     */
    private double maxBatteryCharge;

    /**
     * @param index
     */
    public IMVehicle(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public double getMaxBatteryCharge() {
        return maxBatteryCharge;
    }

    /**
     *
     * @param maxBatteryCharge
     */
    public void setMaxBatteryCharge(double maxBatteryCharge) {
        this.maxBatteryCharge = maxBatteryCharge;
    }
}
