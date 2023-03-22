package parking;

import parking.gates.actions.EntryAttempt;
import parking.gates.Entry;
import parking.gates.Exit;
import vehicles.Car;

/**
 * Этот класс, реализующий элементарный функционал,
 * поможет вам в управлении вашей парковкой.
 *
 * @author Danila Fedortsov
 */
public class Parking {
    /**
     * Максимальное количество парковочных мест.
     */
    private final int maxNumberOfParkingLots;

    /**
     * Список, хранящий все попытки въезда.
     */
    private EntryAttempt[] attempts;

    /**
     * Список, хранящий все въезды на парковку.
     */
    private final Entry[] entries;

    /**
     * Список, хранящий все выезды из парковки.
     */
    private final Exit[] exits;

    /**
     * Список, хранящий информацию об автомобилях, находящихся на парковке.
     */
    private Car[] cars;

    /**
     * Создаёт {@link Parking}. <br>
     * Список автомобилей <code>cars</code> и попыток въезда <code>attempts</code> задаются самостоятельно, так как в
     * любом случае для новой парковки по умолчанию они пустые.
     *
     * @param maxNumberOfParkingLots максимальное количество парковочных мест
     * @param entries                список въездов
     * @param exits                  список выездов
     */
    public Parking(int maxNumberOfParkingLots, Entry[] entries, Exit[] exits) {
        this.maxNumberOfParkingLots = maxNumberOfParkingLots;
        this.entries = entries;
        this.exits = exits;
        this.attempts = new EntryAttempt[0];
        this.cars = new Car[0];
    }

    /**
     * Реализует въезд на парковку. <br>
     * Автомобиль <code>car</code> добавляется в список автомобилей на парковке <code>cars</code> и в список
     * автомобилей проехавших через въезд с номером <code>entryNumber</code>. Так же создается объект типа
     * {@link EntryAttempt} и добавляется в список попыток въезда <code>attempts</code>. В случае неудачной
     * попытки на экран выведется соответствующее сообщение.
     *
     * @param car         автомобиль
     * @param entryNumber номер въезда
     */
    public void enter(Car car, String entryNumber) {
        boolean success = true;

        if (cars.length == maxNumberOfParkingLots) {
            success = false;
        } else {
            if (getEntry(entryNumber) == null) {
                System.out.println("Такого въезда не существует! Повторите попытку\n");
                return;
            }
            getEntry(entryNumber).addCar(car);

            Car[] newCars = new Car[cars.length + 1];
            newCars[0] = car;
            System.arraycopy(cars, 0, newCars, 1, cars.length);

            this.cars = newCars;
        }

        EntryAttempt[] newAttempts = new EntryAttempt[attempts.length + 1];
        newAttempts[0] = new EntryAttempt(success, car.getNumber(), entryNumber);
        System.arraycopy(attempts, 0, newAttempts, 1, attempts.length);

        this.attempts = newAttempts;
    }

    /**
     * Реализует выезд из парковки. <br>
     * Автомобиль с номером <code>carNumber</code> удаляется из списка автомобилей на парковке <code>cars</code> и
     * добавляется в список автомобилей проехавших через выезд с номером <code>exitNumber</code>. В случае неудачной
     * попытки на экран выведется соответствующее сообщение.
     *
     * @param carNumber  номер автомобиля
     * @param exitNumber номер въезда
     */
    public void leave(String carNumber, String exitNumber) {
        if (getExit(exitNumber) == null) {
            System.out.println("Такого выезда не существует! Повторите попытку\n");
            return;
        }

        if (cars.length == 0) {
            System.out.println("На парковке и так нет машин!\n");
            return;
        }

        if (getCar(carNumber) == null) {
            System.out.println("Такого автомобиля на парковке нет!\n");
            return;
        }

        getExit(exitNumber).addCar(getCar(carNumber));

        Car[] newCars = new Car[cars.length - 1];
        System.arraycopy(cars, 1, newCars, 0, newCars.length);

        this.cars = newCars;
    }

    /**
     * Выводит на экран сообщение о том, возможно ли сейчас припарковаться.
     */
    public void printCanEnter() {
        System.out.println((cars.length == maxNumberOfParkingLots ? "Нельзя заехать" : "Можно заехать") + "\n");
    }

    /**
     * Выводит на экран количество свободных мест на парковке.
     */
    public void printNumberAvailable() {
        System.out.println("Свободных мест: " + (maxNumberOfParkingLots - cars.length) + "\n");
    }

    /**
     * Выводит на экран список автомобилей проехавших через въезд с номером <code>entryNumber</code>. Если был введён
     * номер несуществующего въезда, то выводится соответствующее сообщение.
     *
     * @param entryNumber номер въезда
     */
    public void printListCarsEntry(String entryNumber) {
        if (getEntry(entryNumber) == null) {
            System.out.println("Такого въезда не существует! Повторите попытку.\n");
            return;
        }
        System.out.println("Список проехавших через въезд " + entryNumber + ":");
        System.out.println(getEntry(entryNumber).carListToString());
    }

    /**
     * Выводит на экран список автомобилей проехавших через выезд с номером <code>exitNumber</code>. Если был введён
     * номер несуществующего выезда, то выводится соответствующее сообщение.
     *
     * @param exitNumber номер выезда
     */
    public void printListCarsExit(String exitNumber) {
        if (getExit(exitNumber) == null) {
            System.out.println("Такого выезда не существует! Повторите попытку");
            return;
        }
        System.out.println("Список проехавших через выезд " + exitNumber + ":");
        System.out.println(getExit(exitNumber).carListToString());
    }

    /**
     * Выводит на экран список неудавшихся въездов или соответствующее сообщение если таковых нет.
     */
    public void printFailedAttempts() {
        if (attempts.length != 0) {
            System.out.println("Неудачные попытки въезда:");
            for (EntryAttempt attempt : this.attempts) {
                if (!attempt.isSuccess()) {
                    System.out.println(attempt.carAndTimeToString());
                }
            }
        } else {
            System.out.println("Неудачных попыток не было.");
        }
    }

    /**
     * Позволяет получить объект типа {@link Entry} (то есть въезд на парковку) по номеру въезда. Если въезд не был
     * найден, то возвращает <code>null</code>.
     * @param entryNumber номер въезда
     * @return            въезд
     */
    private Entry getEntry(String entryNumber) {
        for (Entry entry : this.entries) {
            if (entry.getNumber().compareTo(entryNumber) == 0) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Позволяет получить объект типа {@link Exit} (то есть выезд из парковки) по номеру въезда. Если выезд не был
     * найден, то возвращает <code>null</code>.
     * @param exitNumber номер выезда
     * @return           выезд
     */
    private Exit getExit(String exitNumber) {
        for (Exit exit : this.exits) {
            if (exit.getNumber().compareTo(exitNumber) == 0) {
                return exit;
            }
        }
        return null;
    }

    /**
     * Позволяет получить объект типа {@link Car} (то есть автомобиль, находящийся на парковке) по номеру автомобиля.
     * Если автомобиль не был найден, то возвращает <code>null</code>.
     * @param carNumber номер автомобиля
     * @return          автомобиль
     */
    private Car getCar(String carNumber) {
        for (Car car : this.cars) {
            if (car.getNumber().compareTo(carNumber) == 0) {
                return car;
            }
        }
        return null;
    }
}
