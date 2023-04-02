package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

/**
 * Средство индивидуальной мобильности (СИМ).
 *
 * @author Danila Fedortsov
 */
public abstract class IMVehicle extends Vehicle {
    /**
     * Декоративное поле. <br>
     * Максимальная ёмкость батреи.
     */
    private double maxBatteryCharge;

    /**
     * Создаёт {@link IMVehicle}.
     *
     * @param index номер СИМ
     */
    public IMVehicle(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю maxBatteryCharge.
     *
     * @return максимальная ёмкость батреи
     */
    public double getMaxBatteryCharge() {
        return maxBatteryCharge;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю maxBatteryCharge.
     *
     * @param maxBatteryCharge новая максимальная ёмкость батареи
     */
    public void setMaxBatteryCharge(double maxBatteryCharge) {
        this.maxBatteryCharge = maxBatteryCharge;
    }
}
