package vehicles;

/**
 * Автомобиль.
 *
 * @author Danila Fedortsov
 */
public class Car {
    /**
     * Регистрационный номер автомобиля.
     */
    private String number;

    /**
     * Создаёт {@link Car}. <br>
     * В случае, если номер был введён неккоректно выводит на экран соответствующее сообщение об этом и самостоятельно
     * заполняет поле <code>number</code>.
     *
     * @param number номер автомобиля
     */
    public Car(String number) {
        if (number.length() == 3) {
            this.number = number;
        } else {
            System.out.println("Некорректный формат автомобильного номера! \n" +
                    "Будут присвоены только первые 3 знака. \n" +
                    "Недостающие знаки будут заменены на 0.\n");

            number += "000";

            this.number = number.substring(0, 3);
        }
    }

    /**
     * Возвращает регистрационный номер автомобиля.
     *
     * @return номер автомобиля
     */
    public String getNumber() {
        return number;
    }

    /**
     * Представление объекта {@link Car} в виде строки.
     *
     * @return строчное представление объекта
     */
    @Override
    public String toString() {
        return "Car: " + number;
    }
}
