package ru.croc.javaschool.sveterkom.rent;

import java.time.LocalDate;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;
import ru.croc.javaschool.sveterkom.vehicles.aircrafts.*;
import ru.croc.javaschool.sveterkom.vehicles.IMVehicles.*;
import ru.croc.javaschool.sveterkom.vehicles.motorcars.*;

/**
 * Управление сервисом по аренде транспортных средств (ТС).
 *
 * @author Danila Fedortsov
 */
public class RentManagement {
    /**
     * Индекс последнего добавленного ТС.
     */
    private int INDEX;

    /**
     * Массив содержащий количество ТС каждого вида. <br>
     * Для реализации в данном случае подразумевается, что vehicleCounts.length = 6,
     * что соответствует количеству видов ТС. Позиции ТС: <br>
     * 0 - Бизнес-джеты; <br>
     * 1 - Вертолёты; <br>
     * 2 - Электросамокаты; <br>
     * 3 - Гироскутеры; <br>
     * 4 - Легковые автомобили; <br>
     * 5 - Грузовые автомобили. <br>
     * В каждой из позиций лежит число (int) означающее количество ТС данного вида.
     */
    private final int[] vehicleCounts;

    /**
     * Список транспорта в распоряжении сервиса.
     */
    private Vehicle[] vehicles;

    /**
     * Список записей об аренде. <br>
     * Сначала новые записи, потом старые.
     */
    private RentRecord[] records;

    /**
     * Создаёт {@link RentManagement}. <br>
     * Инициализирует массивы списков автомобилей и записей об аренде,
     * а так же массив с количеством ТС и стартовый индекс.
     */
    public RentManagement() {
        this.INDEX = 0;
        this.vehicles = new Vehicle[0];
        this.records = new RentRecord[0];
        this.vehicleCounts = new int[6];
    }

    /**
     * Отдаёт ТС в аренду. <br>
     * Добавляет запись об аренде.
     *
     * @param vehicleIndex индекс ТС
     * @param startDate дата начала периода
     * @param endDate дата конца периода
     * @return true - ТС арендовано, false - ТС занято или не найдено
     */
    public boolean rent(int vehicleIndex, LocalDate startDate, LocalDate endDate) {
        if (getVehicle(vehicleIndex) == null) {
            return false;
        }
        for (RentRecord rr : records) {
            if (rr.getVehicleIndex() == vehicleIndex && rr.isActive(startDate, endDate)) {
                return false;
            }
        }
        addToRecords(new RentRecord(vehicleIndex, startDate, endDate));
        return true;
    }

    /**
     * Возвращает ТС из аренды. <br>
     * В случае возврата не в назначенную дату обновляет соответствующую запись об аренде.
     * Необходимо передавать действительную дату начала аренды, иначе запись об аренде не будет найдена.
     *
     * @param vehicleIndex индекс ТС
     * @param startDate дата начала аренды
     * @param endDate фактическая дата конца аренды
     * @return true - ТС возвращено, false - ТС или запись об аренде не найдены
     */
    public boolean returnFromRent(int vehicleIndex, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = getVehicle(vehicleIndex);
        RentRecord record = getRecord(vehicleIndex, startDate);
        if (vehicle == null || record == null) {
            return false;
        }
        record.setEndDate(endDate);
        return true;
    }

    /**
     * Добавляет новое ТС. <br>
     * Присваивает ему уникальный индекс.
     *
     * @param vehicle новое ТС
     * @return true - ТС добавлено, false - попытка положить null
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        vehicle.setIndex(getINDEX());
        addToVehiclesList(vehicle);

        if (vehicle instanceof BusinessJet) {
            this.vehicleCounts[0]++;
        } else if (vehicle instanceof Helicopter) {
            this.vehicleCounts[1]++;
        } else if (vehicle instanceof ElectricScooter) {
            this.vehicleCounts[2]++;
        } else if (vehicle instanceof GyroScooter) {
            this.vehicleCounts[3]++;
        } else if (vehicle instanceof Car) {
            this.vehicleCounts[4]++;
        } else if (vehicle instanceof Truck) {
            this.vehicleCounts[5]++;
        }

        return true;
    }

    /**
     * Списывает ТС.
     *
     * @param index индекс ТС
     * @return true - ТС списано, false - ТС не найдено.
     */
    public boolean decommissioningVehicle(int index) {
        return delFromVehiclesList(index);
    }

    /**
     * Создаёт список содержащий все ТС свободные для аренды в заданный временной промежуток.
     *
     * @param startDate начало временного интервала
     * @param endDate конц временного интервала
     * @return список свободных ТС по категориям
     */
    public String printFreeCategories(LocalDate startDate, LocalDate endDate) {
        String initialLine = "В период: " + startDate.toString() + " --- " + endDate.toString() + " свободны следующие ТС:\n";
        StringBuilder answer = new StringBuilder(initialLine);

        Aircraft[] freeAircraft = searchFreeAircraft(startDate, endDate);
        IMVehicle[] freeIMVehicles = searchFreeIMVehicle(startDate, endDate);
        Motorcar[] freeMotorcars = searchFreeMotorcars(startDate, endDate);

        answer.append("Воздушные суда (").append(freeAircraft.length).append("):\n");
        for (Aircraft a : freeAircraft) {
            answer.append("ВТС ").append(a.getIndex()).append("\n");
        }
        answer.append("\n");

        answer.append("Средства индивидульной мобильности (").append(freeIMVehicles.length).append("):\n");
        for (IMVehicle im : freeIMVehicles) {
            answer.append("СИМ ").append(im.getIndex()).append("\n");
        }
        answer.append("\n");

        answer.append("Автомобильный транспорт (").append(freeMotorcars.length).append("):\n");
        for (Motorcar m : freeMotorcars) {
            answer.append("АТС ").append(m.getIndex()).append("\n");
        }
        answer.append("\n").append("-------------------------------------------------");

        return answer.toString();
    }

    /**
     * Создаёт отчёт в текстовом формате о количестве совбодных и арендованных ТС каждого вида
     * в пределах категорий на момент заданной даты.
     *
     * @param searchDate дата отчётности
     * @return список видов ТС по категориям с данными об арендованных и свободных ТС
     */
    public String printReport(LocalDate searchDate) {
        String initialLine = "Отчет за " + searchDate.toString() + " (свободно / занято):\n";
        StringBuilder answer = new StringBuilder(initialLine);

        int freeBusinessJets = searchFreeBusinessJets(searchDate, searchDate).length;
        int freeHelicopters = searchFreeHelicopters(searchDate, searchDate).length;
        int freeElScooters = searchFreeElectricScooters(searchDate, searchDate).length;
        int freeGyroScooters = searchFreeGyroScooters(searchDate, searchDate).length;
        int freeCars = searchFreeCars(searchDate, searchDate).length;
        int freeTrucks = searchFreeTrucks(searchDate, searchDate).length;

        answer.append("Воздушные суда:\n");
        answer.append(
                String.format("Бизнес-джеты: (%s / %s)%n", freeBusinessJets, vehicleCounts[0] - freeBusinessJets)
        );
        answer.append(
                String.format("Вертолёты:    (%s / %s)%n", freeHelicopters, vehicleCounts[1] - freeHelicopters)
        );
        answer.append("\n");

        answer.append("Средства индивидуальной мобильности:\n");
        answer.append(
                String.format("Электросамокаты: (%s / %s)%n", freeElScooters, vehicleCounts[2] - freeElScooters)
        );
        answer.append(
                String.format("Гироскутеры:     (%s / %s)%n", freeGyroScooters, vehicleCounts[3] - freeGyroScooters)
        );
        answer.append("\n");

        answer.append("Автомобильный транспорт:\n");
        answer.append(
                String.format("Легковые автомобили: (%s / %s)%n", freeCars, vehicleCounts[4] - freeCars)
        );
        answer.append(
                String.format("Грузовые автомобили: (%s / %s)%n", freeTrucks, vehicleCounts[5] - freeTrucks)
        );
        answer.append("\n").append("-------------------------------------------------");

        return answer.toString();
    }

    /**
     * Ищет все свободные ТС вида "Бизнес-джет" в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных бизнес-джетов в период startDate-endDate
     */
    public BusinessJet[] searchFreeBusinessJets(LocalDate startDate, LocalDate endDate) {
        Aircraft[] freeAircraft = searchFreeAircraft(startDate, endDate);
        BusinessJet[] businessJets = new BusinessJet[0];
        for (Aircraft a : freeAircraft) {
            if (a instanceof BusinessJet) {
                BusinessJet[] newFreeBusinessJets = new BusinessJet[businessJets.length + 1];
                System.arraycopy(businessJets, 0, newFreeBusinessJets, 0, businessJets.length);
                newFreeBusinessJets[businessJets.length] = (BusinessJet) a;
                businessJets = newFreeBusinessJets;
            }
        }
        return businessJets;
    }

    /**
     * Ищет все свободные ТС вида "Вертолёт" в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных вертолётов в период startDate-endDate
     */
    public Helicopter[] searchFreeHelicopters(LocalDate startDate, LocalDate endDate) {
        Aircraft[] freeAircraft = searchFreeAircraft(startDate, endDate);
        Helicopter[] helicopters = new Helicopter[0];
        for (Aircraft a : freeAircraft) {
            if (a instanceof Helicopter) {
                Helicopter[] newFreeHelicopters = new Helicopter[helicopters.length + 1];
                System.arraycopy(helicopters, 0, newFreeHelicopters, 0, helicopters.length);
                newFreeHelicopters[helicopters.length] = (Helicopter) a;
                helicopters = newFreeHelicopters;
            }
        }
        return helicopters;
    }

    /**
     * Ищет все свободные ТС вида "Электросамокат" в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных электросамокатов в период startDate-endDate
     */
    public ElectricScooter[] searchFreeElectricScooters(LocalDate startDate, LocalDate endDate) {
        IMVehicle[] freeIMVehicle = searchFreeIMVehicle(startDate, endDate);
        ElectricScooter[] scooters = new ElectricScooter[0];
        for (IMVehicle im : freeIMVehicle) {
            if (im instanceof ElectricScooter) {
                ElectricScooter[] newFreeScooters = new ElectricScooter[scooters.length + 1];
                System.arraycopy(scooters, 0, newFreeScooters, 0, scooters.length);
                newFreeScooters[scooters.length] = (ElectricScooter) im;
                scooters = newFreeScooters;
            }
        }
        return scooters;
    }

    /**
     * Ищет все свободные ТС вида "Гироскутер" в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных гироскутеров в период startDate-endDate
     */
    public GyroScooter[] searchFreeGyroScooters(LocalDate startDate, LocalDate endDate) {
        IMVehicle[] freeIMVehicle = searchFreeIMVehicle(startDate, endDate);
        GyroScooter[] scooters = new GyroScooter[0];
        for (IMVehicle im : freeIMVehicle) {
            if (im instanceof GyroScooter) {
                GyroScooter[] newFreeScooters = new GyroScooter[scooters.length + 1];
                System.arraycopy(scooters, 0, newFreeScooters, 0, scooters.length);
                newFreeScooters[scooters.length] = (GyroScooter) im;
                scooters = newFreeScooters;
            }
        }
        return scooters;
    }

    /**
     * Ищет все свободные ТС вида "Легковой автомобиль" в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных легковых автомобилей в период startDate-endDate
     */
    public Car[] searchFreeCars(LocalDate startDate, LocalDate endDate) {
        Motorcar[] freeMotorcars = searchFreeMotorcars(startDate, endDate);
        Car[] cars = new Car[0];
        for (Motorcar m : freeMotorcars) {
            if (m instanceof Car) {
                Car[] newFreeCars = new Car[cars.length + 1];
                System.arraycopy(cars, 0, newFreeCars, 0, cars.length);
                newFreeCars[cars.length] = (Car) m;
                cars = newFreeCars;
            }
        }
        return cars;
    }

    /**
     * Ищет все свободные ТС вида "Грузовой автомобиль" в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных грузовых автомобилей в период startDate-endDate
     */
    public Truck[] searchFreeTrucks(LocalDate startDate, LocalDate endDate) {
        Motorcar[] freeMotorcars = searchFreeMotorcars(startDate, endDate);
        Truck[] trucks = new Truck[0];
        for (Motorcar m : freeMotorcars) {
            if (m instanceof Truck) {
                Truck[] newFreeTrucks = new Truck[trucks.length + 1];
                System.arraycopy(trucks, 0, newFreeTrucks, 0, trucks.length);
                newFreeTrucks[trucks.length] = (Truck) m;
                trucks = newFreeTrucks;
            }
        }
        return trucks;
    }

    /**
     * Ищет все свободные ТС категории "Воздушное судно" (ВС) в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных ВС в период startDate-endDate
     */
    public Aircraft[] searchFreeAircraft(LocalDate startDate, LocalDate endDate) {
        Vehicle[] freeVehicles = searchFreeVehicles(startDate, endDate);
        Aircraft[] freeAircraft = new Aircraft[0];
        for (Vehicle v : freeVehicles) {
            if (v instanceof Aircraft) {
                Aircraft[] newFreeAircraft = new Aircraft[freeAircraft.length + 1];
                System.arraycopy(freeAircraft, 0, newFreeAircraft, 0, freeAircraft.length);
                newFreeAircraft[freeAircraft.length] = (Aircraft) v;
                freeAircraft = newFreeAircraft;
            }
        }
        return freeAircraft;
    }

    /**
     * Ищет все свободные ТС категории "Средства индивидуально мобильности" (СИМ) в период
     * с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных СИМ в период startDate-endDate
     */
    public IMVehicle[] searchFreeIMVehicle(LocalDate startDate, LocalDate endDate) {
        Vehicle[] freeVehicles = searchFreeVehicles(startDate, endDate);
        IMVehicle[] freeIMVehicles = new IMVehicle[0];
        for (Vehicle v : freeVehicles) {
            if (v instanceof IMVehicle) {
                IMVehicle[] newFreeIMVehicles = new IMVehicle[freeIMVehicles.length + 1];
                System.arraycopy(freeIMVehicles, 0, newFreeIMVehicles, 0, freeIMVehicles.length);
                newFreeIMVehicles[freeIMVehicles.length] = (IMVehicle) v;
                freeIMVehicles = newFreeIMVehicles;
            }
        }
        return freeIMVehicles;
    }

    /**
     * Ищет все свободные ТС категории "Автомобильный транспорт" (АТ) в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободного АТ в период startDate-endDate
     */
    public Motorcar[] searchFreeMotorcars(LocalDate startDate, LocalDate endDate) {
        Vehicle[] freeVehicles = searchFreeVehicles(startDate, endDate);
        Motorcar[] freeMotorcars = new Motorcar[0];
        for (Vehicle v : freeVehicles) {
            if (v instanceof Motorcar) {
                Motorcar[] newFreeMotorcars = new Motorcar[freeMotorcars.length + 1];
                System.arraycopy(freeMotorcars, 0, newFreeMotorcars, 0, freeMotorcars.length);
                newFreeMotorcars[freeMotorcars.length] = (Motorcar) v;
                freeMotorcars = newFreeMotorcars;
            }
        }
        return freeMotorcars;
    }

    /**
     * Ищет все свободные ТС в период с startDate по endDate включительно.
     *
     * @param startDate начало временного интервала
     * @param endDate конец временного интервала
     * @return список свободных ТС в период startDate-endDate
     */
    public Vehicle[] searchFreeVehicles(LocalDate startDate, LocalDate endDate) {
        Vehicle[] freeVehicles = new Vehicle[vehicles.length];
        System.arraycopy(vehicles, 0, freeVehicles, 0, vehicles.length);

        for (Vehicle vehicle : vehicles) {
            for (RentRecord rr : records) {
                if (rr.getVehicleIndex() == vehicle.getIndex() && rr.isActive(startDate, endDate)) {
                    Vehicle[] newFreeVehicles = new Vehicle[freeVehicles.length - 1];
                    int k = 0;
                    for (Vehicle freeVehicle : freeVehicles) {
                        if (vehicle.getIndex() == freeVehicle.getIndex()) {
                            continue;
                        }
                        newFreeVehicles[k] = freeVehicle;
                        k++;
                    }
                    freeVehicles = newFreeVehicles;
                }
            }
        }
        return freeVehicles;
    }

    /**
     * Добавляет новое ТС в конец списка vehicles.
     *
     * @param vehicle ТС
     */
    private void addToVehiclesList(Vehicle vehicle) {
        Vehicle[] newVehicles = new Vehicle[vehicles.length + 1];
        System.arraycopy(vehicles, 0, newVehicles, 0, vehicles.length);
        newVehicles[vehicles.length] = vehicle;
        this.vehicles = newVehicles;
    }

    /**
     * Удаляет ТС из списка vehicles по заданному индексу ТС. <br>
     * Уменьшает количество ТС соответсвующего вида в vehiclesCount.
     *
     * @param vehicleIndex индекс ТС
     * @return true - ТС удалено, false - ТС не найдено
     */
    private boolean delFromVehiclesList(int vehicleIndex) {
        Vehicle remVehicle = getVehicle(vehicleIndex);
        if (remVehicle == null) {
            return false;
        }
        Vehicle[] newVehicles = new Vehicle[vehicles.length - 1];
        int num = 0;
        for (Vehicle v : vehicles) {
            if (v.getIndex() == vehicleIndex) {
                remVehicle = v;
                continue;
            }
            newVehicles[num] = v;
            num++;
        }

        if (remVehicle instanceof BusinessJet) {
            this.vehicleCounts[0]--;
        } else if (remVehicle instanceof Helicopter) {
            this.vehicleCounts[1]--;
        } else if (remVehicle instanceof ElectricScooter) {
            this.vehicleCounts[2]--;
        } else if (remVehicle instanceof GyroScooter) {
            this.vehicleCounts[3]--;
        } else if (remVehicle instanceof Car) {
            this.vehicleCounts[4]--;
        } else if (remVehicle instanceof Truck) {
            this.vehicleCounts[5]--;
        }

        this.vehicles = newVehicles;
        return true;
    }

    /**
     * Возвращает ТС по заданному индексу ТС.
     *
     * @param vehicleIndex индекс ТС
     * @return ТС, либо null, если ТС не найдено
     */
    private Vehicle getVehicle(int vehicleIndex) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicleIndex == vehicle.getIndex()) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Добавляет запись об аренде в начало списка записей records.
     *
     * @param record запись об аренде
     */
    private void addToRecords(RentRecord record) {
        RentRecord[] newRecords = new RentRecord[records.length + 1];
        System.arraycopy(records, 0, newRecords, 1, records.length);
        newRecords[0] = record;
        this.records = newRecords;
    }

    /**
     * Возвращает запись об аренде с заданным индексом ТС и датой начала аренды.
     *
     * @param vehicleIndex индекс ТС
     * @param startDate дата начала аренды
     * @return запись об аренде, либо null если записи не найдено
     */
    private RentRecord getRecord(int vehicleIndex, LocalDate startDate) {
        for (RentRecord r : this.records) {
            if (r.getVehicleIndex() == vehicleIndex && r.getStartDate().isEqual(startDate)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Позволяет присваивать уникальный индекс каждому вновь прибывшему ТС. <br>
     * Новый индекс = предыдущий индекс + 1.
     * @return уникальный индекс автомобиля
     */
    private int getINDEX() {
        INDEX += 1;
        return INDEX;
    }
}
