package ru.croc.javaschool.sveterkom.rent;

import java.time.LocalDate;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;
import ru.croc.javaschool.sveterkom.vehicles.aircrafts.*;
import ru.croc.javaschool.sveterkom.vehicles.IMVehicles.*;
import ru.croc.javaschool.sveterkom.vehicles.motorcars.*;

/**
 *
 */
public class RentManagement {
    /**
     *
     */
    private int INDEX;

    /**
     *
     */
    private int[] vehicleCounts;

    /**
     *
     */
    private Vehicle[] vehicles;

    /**
     *
     */
    private RentRecord[] records;

    /**
     *
     */
    public RentManagement() {
        this.INDEX = 0;
        this.vehicles = new Vehicle[0];
        this.records = new RentRecord[0];
        this.vehicleCounts = new int[6];
    }

    /**
     *
     * @param vehicleIndex
     * @param startDate
     * @param endDate
     * @return
     */
    public boolean rent(int vehicleIndex, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = getVehicle(vehicleIndex);
        if (vehicle == null) {
            return false;
        }
        addToRecords(new RentRecord(vehicleIndex, startDate, endDate));
        return true;
    }

    /**
     *
     * @param vehicleIndex
     * @param endDate
     * @return
     */
    public boolean returnFromRent(int vehicleIndex, LocalDate endDate) {
        Vehicle vehicle = getVehicle(vehicleIndex);
        RentRecord record = getRecord(vehicleIndex);
        if (vehicle == null || record == null) {
            return false;
        }
        record.setEndDate(endDate);
        return true;
    }

    /**
     *
     * @param vehicle
     * @return
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
     *
     * @param index
     * @return
     */
    public boolean decommissioningVehicle(int index) {
        return delFromVehiclesList(index);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public String printFreeCategories(LocalDate startDate, LocalDate endDate) {
        String initialLine = "В период: " + startDate.toString() + " --- " + endDate.toString() + " свободны следующие ТС:\n";
        StringBuilder answer = new StringBuilder(initialLine);

        Aircraft[] freeAircraft = searchFreeAircraft(startDate, endDate);
        IMVehicle[] freeIMVehicles = searchFreeIMVehicle(startDate, endDate);
        Motorcar[] freeMotorcars = searchFreeMotorcars(startDate, endDate);

        answer.append("Воздушный транспорт (").append(freeAircraft.length).append("):\n");
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
     *
     * @param searchDate
     * @return
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

        answer.append("Воздушный транспорт:\n");
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
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
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public Vehicle[] searchFreeVehicles(LocalDate startDate, LocalDate endDate) {
        Vehicle[] searchVehicles = new Vehicle[0];
        for (RentRecord rec : records) {
            if (!rec.isActive(startDate, endDate)) {
                Vehicle[] newSearchVehicles = new Vehicle[searchVehicles.length + 1];
                System.arraycopy(searchVehicles, 0, newSearchVehicles, 0, searchVehicles.length);
                newSearchVehicles[searchVehicles.length] = getVehicle(rec.getVehicleIndex());
                searchVehicles = newSearchVehicles;
            }
        }

        return searchVehicles;
    }

    /**
     *
     * @param vehicle
     */
    private void addToVehiclesList(Vehicle vehicle) {
        Vehicle[] newVehicles = new Vehicle[vehicles.length + 1];
        System.arraycopy(vehicles, 0, newVehicles, 0, vehicles.length);
        newVehicles[vehicles.length] = vehicle;
        this.vehicles = newVehicles;
    }

    /**
     *
     * @param vehicleIndex
     * @return
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
     *
     * @param vehicleIndex
     * @return
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
     *
     * @param record
     */
    private void addToRecords(RentRecord record) {
        RentRecord[] newRecords = new RentRecord[records.length + 1];
        System.arraycopy(records, 0, newRecords, 1, records.length);
        newRecords[0] = record;
        this.records = newRecords;
    }

    /**
     *
     * @param vehicleIndex
     * @return
     */
    private RentRecord getRecord(int vehicleIndex) {
        for (RentRecord r : this.records) {
            if (r.getVehicleIndex() == vehicleIndex) {
                return r;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    private int getINDEX() {
        INDEX += 1;
        return INDEX;
    }
}
