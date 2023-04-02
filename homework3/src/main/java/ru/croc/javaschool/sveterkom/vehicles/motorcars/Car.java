package ru.croc.javaschool.sveterkom.vehicles.motorcars;

/**
 *
 */
public class Car extends Motorcar {
    /**
     *
     */
    private int numOfSeats;

    /**
     * @param index
     */
    public Car(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public int getNumOfSeats() {
        return numOfSeats;
    }

    /**
     *
     * @param numOfSeats
     */
    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }
}
