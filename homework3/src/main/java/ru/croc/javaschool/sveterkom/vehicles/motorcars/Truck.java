package ru.croc.javaschool.sveterkom.vehicles.motorcars;

/**
 * Грузовой автомобиль.
 *
 * @author Danila Fedortsov
 */
public class Truck extends Motorcar {
    /**
     * Декоративное поле. <br>
     * Грузоподъёмность.
     */
    private double loadCapacity;

    /**
     * Создаёт {@link Truck}.
     *
     * @param index номер грузового автомобиля
     */
    public Truck(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю loadCapacity.
     *
     * @return грузоподъёмность
     */
    public double getLoadCapacity() {
        return loadCapacity;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю loadCapacity.
     *
     * @param loadCapacity грузоподъёмность
     */
    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
