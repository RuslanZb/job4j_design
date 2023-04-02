package ru.job4j.ood.parking;


public class Parking implements Store {

    private Vehicle[] carPlace;
    private Vehicle[] truckPlace;

    public Parking(int numCarPlace, int numTruckPlace) {
        carPlace = new Vehicle[numCarPlace];
        truckPlace = new Vehicle[numTruckPlace];
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean rsl = false;
        if (vehicle.getSize() > 1 && addToArray(truckPlace, vehicle)) {
            rsl = true;
        } else if (vehicle.getSize() == 1 && addToArray(carPlace, vehicle)) {
            rsl = true;
        } else if (vehicle.getSize() > 1 && !addToArray(truckPlace, vehicle)) {
            if (addTruckToCar(carPlace, vehicle)) {
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Vehicle remove(String key) {
        Vehicle rsl = removeFromArray(carPlace, key);
        if (rsl == null) {
            rsl = removeFromArray(truckPlace, key);
        }
        return rsl;
    }

    private boolean addToArray(Vehicle[] vehicles, Vehicle vehicle) {
        boolean rsl = false;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) {
                vehicles[i] = vehicle;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private Vehicle removeFromArray(Vehicle[] vehicles, String name) {
        Vehicle rsl = null;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && name.equals(vehicles[i].getNumber())) {
                rsl = vehicles[i];
                vehicles[i] = null;
            }
        }
        return rsl;
    }

    private boolean addTruckToCar(Vehicle[] vehicles, Vehicle vehicle) {
        boolean rsl = false;
        int len = 0;
        for (int i = 0; i < vehicles.length; i++) {
            len++;
            if (vehicles[i] != null) {
                len = 0;
            }
            if (vehicle.getSize() == len) {
                for (int j = 0; j < len; j++) {
                    vehicles[i - j] = vehicle;
                }
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}
