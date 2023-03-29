package ru.croc.javaschool.sveterkom.vehicles.actions;

/**
 *
 */
public interface Rentable {
    /**
     *
     */
    void rent();

    /**
     *
     */
    void returnFromRent();

    /**
     *
     * @return
     */
    boolean isRented();
}
