package ru.croc.javaschool.sveterkom.vehicles;

import ru.croc.javaschool.sveterkom.vehicles.actions.*;

/**
 *
 */
public abstract class Vehicle implements Serviceable, Rentable {
    /**
     *
     */
    private int index;

    /**
     *
     */
    private boolean isRented;

    /**
     *
     */
    private boolean isBroken;

    /**
     *
     * @param index
     * @param isBroken
     */
    public Vehicle(int index, boolean isBroken) {
        this.index = index;
        this.isBroken = isBroken;
        this.isRented = false;
    }

    /**
     *
     * @param index
     */
    public Vehicle(int index) {
        this(index, false);
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     */
    @Override
    public void repair() {
        if (isBroken) {
            this.isBroken = false;
        } else {
            System.out.println(index + " и так исправен.\n");
        }
    }

    /**
     *
     */
    @Override
    public void brokeDown() {
        if (isBroken) {
            System.out.println(index + " и так неисправен.\n");
        } else {
            this.isBroken = true;
        }
    }

    /**
     *
     */
    @Override
    public void rent() {
        if (isRented) {
            System.out.println(index + " уже арендован.\n");
        } else {
            this.isRented = true;
        }
    }

    /**
     *
     */
    @Override
    public void returnFromRent() {
        if (isRented) {
            this.isRented = false;
        } else {
            System.out.println(index + " не находится в аренде.\n");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isBroken() {
        return isBroken;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isRented() {
        return isRented;
    }
}
