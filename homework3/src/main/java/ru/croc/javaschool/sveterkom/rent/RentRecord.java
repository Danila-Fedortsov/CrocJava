package ru.croc.javaschool.sveterkom.rent;

import java.time.LocalDate;

/**
 *
 */
public class RentRecord {
    /**
     *
     */
    private int carIndex;

    /**
     *
     */
    private LocalDate startDate;

    /**
     *
     */
    private LocalDate endDate;

    /**
     *
     * @param carIndex
     * @param startDate
     * @param endDate
     */
    public RentRecord(int carIndex, LocalDate startDate, LocalDate endDate) {
        this.carIndex = carIndex;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     *
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public boolean isActive(LocalDate dateOne, LocalDate dateTwo) {
        return !(dateOne.isAfter(startDate) && dateOne.isAfter(endDate) ||
                dateTwo.isBefore(startDate) && dateTwo.isBefore(endDate));
    }

    /**
     *
     * @param endDate
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    public int getVehicleIndex() {
        return carIndex;
    }
}
