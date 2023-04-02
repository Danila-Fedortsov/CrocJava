package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

/**
 * Гироскутер.
 *
 * @author Danila Fedortsov
 */
public class GyroScooter extends IMVehicle{
    /**
     * Декоративное поле. <br>
     * Максимальный угол наклона относительно поверхности земли.
     */
    private double maxSlope;

    /**
     * Создаёт {@link GyroScooter}.
     *
     * @param index номер гироскутера
     */
    public GyroScooter(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю maxSlope.
     * @return максимальный угол наклона относительно поверхности земли
     */
    public double getMaxSlope() {
        return maxSlope;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю maxSlope.
     * @param maxSlope максимальный угол наклона относительно поверхности земли
     */
    public void setMaxSlope(double maxSlope) {
        this.maxSlope = maxSlope;
    }
}
