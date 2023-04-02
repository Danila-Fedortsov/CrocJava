package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

/**
 *
 */
public class GyroScooter extends IMVehicle{
    /**
     *
     */
    private double maxSlope;

    /**
     * @param index
     */
    public GyroScooter(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public double getMaxSlope() {
        return maxSlope;
    }

    /**
     *
     * @param maxSlope
     */
    public void setMaxSlope(double maxSlope) {
        this.maxSlope = maxSlope;
    }
}
