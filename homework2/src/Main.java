import parking.Parking;
import parking.gates.Entry;
import parking.gates.Exit;
import vehicles.Car;

/**
 * Главный класс.
 *
 *
 * @author Danila Fedortsov
 */
public class Main {
    /**
     * Вход в программу.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Entry[] entriesList = new Entry[]{
                new Entry("001"),
                new Entry("002"),
                new Entry("003")
        };

        Exit[] exitsList = new Exit[]{
                new Exit("AAA"),
                new Exit("AAB"),
                new Exit("AAC")
        };

        Car[] cars = new Car[] {
                new Car("000"),
                new Car("111"),
                new Car("22B"),
                new Car("3333"),
                new Car("444"),
                new Car("555"),
        };

        Parking parking = new Parking(5, entriesList, exitsList);

        parking.printCanEnter();
        parking.printNumberAvailable();

        parking.enter(cars[0], "001");
        parking.enter(cars[1], "002");
        parking.enter(cars[2], "003");
        parking.enter(cars[3], "001");
        parking.enter(cars[4], "002");

        parking.printCanEnter();

        parking.enter(cars[5], "003");

        parking.leave("22B", "AAA");

        parking.printListCarsEntry("001");
        parking.printListCarsExit("AAA");

        parking.printNumberAvailable();

        parking.printFailedAttempts();
    }
}