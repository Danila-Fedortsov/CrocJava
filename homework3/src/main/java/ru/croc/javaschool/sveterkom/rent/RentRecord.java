package ru.croc.javaschool.sveterkom.rent;

import java.time.LocalDate;

/**
 * Запись об аренде.
 *
 * @author Danila Fedortsov
 */
public class RentRecord {
    /**
     * Индекс арендованного ТС.
     */
    private final int vehicleIndex;

    /**
     * Дата начала аренды.
     */
    private final LocalDate startDate;

    /**
     * Дата конца аренды.
     */
    private LocalDate endDate;

    /**
     * Создаёт {@link RentRecord}.
     *
     * @param vehicleIndex индекс ТС
     * @param startDate дата начала аренды
     * @param endDate дата завершения аренды
     */
    public RentRecord(int vehicleIndex, LocalDate startDate, LocalDate endDate) {
        this.vehicleIndex = vehicleIndex;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Даёт понять активна ли аренда в период заданных дат.
     *
     * @param dateOne дата начала периода
     * @param dateTwo дата конца периода
     * @return true - аренда активна, false - аренда не активна
     */
    public boolean isActive(LocalDate dateOne, LocalDate dateTwo) {
        return !(dateOne.isAfter(startDate) && dateOne.isAfter(endDate) ||
                dateTwo.isBefore(startDate) && dateTwo.isBefore(endDate));
    }

    /**
     * Задаёт фактическую дату завершения аренды.
     *
     * @param endDate фактическая дата завершения аренды
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Возвращает индекс арендуемого ТС.
     *
     * @return индекс ТС
     */
    public int getVehicleIndex() {
        return vehicleIndex;
    }

    /**
     * Возвращает дату начала аренды.
     *
     * @return дата начала аренды
     */
    public LocalDate getStartDate() {
        return startDate;
    }
}
