package ru.croc.javaschool.sveterkom.rent;

import ru.croc.javaschool.sveterkom.vehicles.Vehicle;

public class RentManagement {
    private int INDEX;
    private Vehicle[] vehicles;
    private RentRecord[] records;

    public RentManagement() {
        this.INDEX = 0;
        this.vehicles = new Vehicle[0];
        this.records = new RentRecord[0];
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Транспорт не был добавлен, попробуйте снова.\n");
            return;
        }
        vehicle.setIndex(getINDEX());
        addToVehiclesList(vehicle);
    }

    public void decommissioningVehicle(int index) {
        delFromVehiclesList(index);
    }

    private void addToVehiclesList(Vehicle vehicle) {
        Vehicle[] newVehicles = new Vehicle[vehicles.length + 1];
        System.arraycopy(vehicles, 0, newVehicles, 0, vehicles.length);
        newVehicles[vehicles.length + 1] = vehicle;
        this.vehicles = newVehicles;
    }

    private void delFromVehiclesList(int index) {
        if (!inVehicleList(index)) {
            System.out.println("Такого транспорта нет.\n");
            return;
        }
        Vehicle[] newVehicle = new Vehicle[vehicles.length - 1];
        int num = 0;
        for (Vehicle v : vehicles) {
            if (v.getIndex() == index) {
                continue;
            }
            newVehicle[num] = v;
            num++;
        }

        this.vehicles = newVehicle;
    }

    private boolean inVehicleList(int index) {
        boolean answer = false;
        for (Vehicle vehicle : vehicles) {
            if (index == vehicle.getIndex()) {
                answer = true;
                break;
            }
        }

        return answer;
    }
    private int getINDEX() {
        INDEX += 1;
        return INDEX;
    }
}
