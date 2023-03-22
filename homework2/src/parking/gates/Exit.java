package parking.gates;

import vehicles.Car;

/**
 * Выезд с парковки.
 *
 * @author Danila Fedortsov
 */
public class Exit {
    /**
     * Идентификационный номер выезда.
     */
    private String number;

    /**
     * Текстовое описание выезда.
     */
    private String description;

    /**
     * Список номеров выехавших автомобилей.
     */
    private Car[] carsList;

    /**
     * Создает {@link Exit}. <br>
     * Список автомобилей <code>carList</code> задаётся самостоятельно, так как для нового выезда список проезжающих
     * через него автомобилей по умолчанию пустой.
     *
     * @param number      номер выезда
     * @param description описание выезда
     */
    public Exit(String number, String description) {
        this.number = number;
        this.description = description;
        this.carsList = new Car[0];
    }

    /**
     * Создает {@link Exit}. <br>
     * На случай если описание не требуется. Описание по умолчанию: <code>"Нормальный такой выезд."</code>
     *
     * @param number номер выезда
     */
    public Exit(String number) {
        this(number, "Нормальный такой выезд.");
    }

    /**
     * Добавляет номер автомобиля в список выехавших.
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
     * Возвращает список проехавших через данный выезд автомобилей в формате строки. В случае если таких автомобилей
     * нет возвращает соответствующее сообщение.
     *
     * @return список автомобилей
     */
    public String carListToString() {
        if (carsList.length == 0) {
            return "Никто не выезжал через выезд " + number + ".";
        }
        StringBuilder list = new StringBuilder();
        for (Car car : carsList) {
            list.append(car.toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Возвращает номер выезда.
     *
     * @return номер выезда
     */
    public String getNumber() {
        return number;
    }
}
