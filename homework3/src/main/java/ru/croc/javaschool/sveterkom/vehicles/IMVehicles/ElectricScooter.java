package ru.croc.javaschool.sveterkom.vehicles.IMVehicles;

/**
 * Электросамокат.
 *
 * @author Danila Fedortsov
 */
public class ElectricScooter extends IMVehicle {
    /**
     * Декоративное поле. <br>
     * Умение складываться.
     */
    private boolean isFolding;

    /**
     * Создаёт {@link ElectricScooter}.
     *
     * @param index номер электросамоката
     */
    public ElectricScooter(int index) {
        super(index);
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю isFolding.
     *
     * @return true - складывается, false - нет
     */
    public boolean isFolding() {
        return isFolding;
    }

    /**
     * Создан, чтобы условно показывать реализацию доступа к полю isFolding.
     *
     * @param folding true - складывается, false - нет
     */
    public void setFolding(boolean folding) {
        isFolding = folding;
    }
}
