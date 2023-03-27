package ru.job4j.ood.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements Store {
    private int numCarPlace;
    private int numTruckPlace;
    private List<Vehicle> carPlace = new ArrayList<>(numCarPlace);
    private List<Vehicle> truckPlace = new ArrayList<>(numTruckPlace);

    public Parking(int numCarPlace, int numTruckPlace) {
        this.numCarPlace = numCarPlace;
        this.numTruckPlace = numTruckPlace;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public Vehicle remove(String key) {
        return null;
    }
}
