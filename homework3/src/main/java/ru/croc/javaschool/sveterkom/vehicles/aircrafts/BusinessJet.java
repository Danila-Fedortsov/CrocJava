package ru.croc.javaschool.sveterkom.vehicles.aircrafts;

/**
 * Бизнес-джет.
 *
 * @author Danila Fedortsov
 */
public class BusinessJet extends Aircraft {
    /**
     * Декоративное поле. <br>
     * Размах крыльев.
     */
    private double wingspan;

    /**
     * Создаёт {@link BusinessJet}.
     *
     * @param index номер бизнес-джета
     */
    public BusinessJet(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю wingspan.
     *
     * @return размах крыльев
     */
    public double getWingspan() {
        return wingspan;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю wingspan.
     *
     * @param wingspan новый размах крыльев
     */
    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

}
