package ru.croc.javaschool.sveterkom.vehicles;

/**
 * Транспорт.
 *
 * @author Danila Fedortsov
 */
public abstract class Vehicle {
    /**
     * Номер транспорта.
     */
    private int index;

    /**
     * Создаёт {@link Vehicle}.
     *
     * @param index номер транспорта
     */
    public Vehicle(int index) {
        this.index = index;
    }

    /**
     * Возвращает номер транспорта.
     *
     * @return номер транспорта
     */
    public int getIndex() {
        return index;
    }

    /**
     * Меняет номер транспорта на новый.
     *
     * @param index новый номер транспорта
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
