package ru.croc.javaschool.sveterkom.vehicles.motorcars;

/**
 * Легковой втомобиль.
 *
 * @author Danila Fedortsov
 */
public class Car extends Motorcar {
    /**
     * Декоративное поле. <br>
     * Количество посадочных мест.
     */
    private int numOfSeats;

    /**
     * Создаёт {@link Car}.
     *
     * @param index номер легкового автомобиля.
     */
    public Car(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю numOfSeats.
     *
     * @return количество посадочных мест
     */
    public int getNumOfSeats() {
        return numOfSeats;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю numOfSeats.
     *
     * @param numOfSeats количество посадочных мест
     */
    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }
}
