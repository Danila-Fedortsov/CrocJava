package ru.croc.javaschool.sveterkom.vehicles.actions;

/**
 *
 */
public interface Serviceable {
    /**
     *
     */
    void repair();

    /**
     *
     */
    void brokeDown();

    /**
     *
     * @return
     */
    boolean isBroken();
}
