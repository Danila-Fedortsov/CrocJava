package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

/**
 * Вертолёт.
 *
 * @author Danila Fedortsov
 */
public class Helicopter extends Aircraft{
    /**
     * Декоративное поле. <br>
     * Число лопастей.
     */
    private int numOfBlades;

    /**
     * Создаёт {@link Helicopter}.
     *
     * @param index номер вертолёта
     */
    public Helicopter(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю numOfBlades.
     *
     * @return число лопастей
     */
    public int getNumOfBlades() {
        return numOfBlades;
    }
}
