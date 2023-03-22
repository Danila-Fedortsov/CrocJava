package parking.gates;

import vehicles.Car;

/**
 * Въезд на парковку.
 *
 * @author Danila Fedortsov
 */
public class Entry {
    /**
     * Идентификационный номер въезда.
     */
    private String number;

    /**
     * Текстовое описание въезда.
     */
    private String description;

    /**
     * Список въехавших автомобилей.
     */
    private Car[] carsList;

    /**
     * Создает {@link Entry}. <br>
     * Список автомобилей <code>carList</code> задаётся самостоятельно, так как для нового въезда список проезжающих
     * через него автомобилей по умолчанию пустой.
     *
     * @param number      номер въезда
     * @param description описание въезда
     */
    public Entry(String number, String description) {
        this.number = number;
        this.description = description;
        this.carsList = new Car[0];
    }

    /**
     * Создает {@link Entry}. <br>
     * На случай если описание не требуется. Описание по умолчанию: <code>"Нормальный такой въезд."</code>
     *
     * @param number номер въезда
     */
    public Entry(String number) {
        this(number, "Нормальный такой въезд.");
    }

    /**
     * Добавляет автомобиль в список въехавших.
     *
     * @param car автомобиль
     */
    public void addCar(Car car) {
        Car[] newCarsList = new Car[carsList.length + 1];

        newCarsList[0] = car;
        System.arraycopy(carsList, 0, newCarsList, 1, carsList.length);

        this.carsList = newCarsList;
    }

    /**
     * Возвращает список проехавших через данный въезд автомобилей в формате строки. В случае если таких автомобилей
     * нет возвращает соответствующее сообщение.
     *
     * @return список автомобилей
     */
    public String carListToString() {
        if (carsList.length == 0) {
            return "Никто не въезжал через въезд " + number + ".";
        }
        StringBuilder list = new StringBuilder();
        for (Car car : carsList) {
            list.append(car.toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Возвращает номер въезда.
     *
     * @return номер въезда
     */
    public String getNumber() {
        return number;
    }
}
