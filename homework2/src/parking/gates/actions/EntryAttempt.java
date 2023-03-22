package parking.gates.actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Попытка въезда автомобилем на парковку.
 *
 * @author Danila Fedortsov
 */
public class EntryAttempt {
    /**
     * Успех попытки.
     */
    private final boolean success;

    /**
     * Номер автомобиля совершившего попытку въезда на парковку.
     */
    private final String carNumber;

    /**
     * Номер въезда, на котором была совершена попытка заехать на парковку.
     */
    private final String entryNumber;

    /**
     * Время совершения попытки въезда на парковку.
     */
    private final String attemptTime;

    /**
     * Создаёт {@link EntryAttempt}. <br>
     * Время <code>attemptTime</code> задаётся самостоятельно при создании объекта.
     *
     * @param success     успех попытки
     * @param carNumber   номер автомобиля
     * @param entryNumber номер въезда
     */
    public EntryAttempt(boolean success, String carNumber, String entryNumber) {
        this.success = success;
        this.carNumber = carNumber;
        this.entryNumber = entryNumber;

        DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("HH:mm dd.MM.yy");
        this.attemptTime = correctFormat.format(LocalDateTime.now());
    }

    /**
     * Возвращает успех попытки въезда на парковку.
     *
     * @return успех попытки
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Возвращает строку, в которой содержится номер автомобиля <code>carNumber</code> совершившего попытку и время
     * совершения попытки <code>attemptTime</code>.
     *
     * @return номер машины и время в формате строки
     */
    public String carAndTimeToString() {
        return carNumber + " " + attemptTime;
    }
}
