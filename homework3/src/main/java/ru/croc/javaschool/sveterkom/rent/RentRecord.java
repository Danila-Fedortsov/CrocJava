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
     * @param searchDate
     * @return
     */
    public boolean isActive(LocalDate searchDate) {
        return !startDate.isAfter(searchDate) && !endDate.isBefore(searchDate);
    }

    /**
     *
     * @return
     */
    public int getCarIndex() {
        return carIndex;
    }
}
