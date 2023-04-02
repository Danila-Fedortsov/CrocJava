package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

/**
 *
 */
public class Helicopter extends Aircraft{
    /**
     *
     */
    private int numOfBlades;

    /**
     * @param index
     */
    public Helicopter(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public int getNumOfBlades() {
        return numOfBlades;
    }

    /**
     *
     * @param numOfBlades
     */
    public void setNumOfBlades(int numOfBlades) {
        this.numOfBlades = numOfBlades;
    }
}
