package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

/**
 *
 */
public class BusinessJet extends Aircraft {
    /**
     *
     */
    private double wingspan;

    /**
     *
     * @param index
     */
    public BusinessJet(int index) {
        super(index);
    }

    /**
     *
     * @return
     */
    public double getWingspan() {
        return wingspan;
    }

    /**
     *
     * @param wingspan
     */
    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

}
