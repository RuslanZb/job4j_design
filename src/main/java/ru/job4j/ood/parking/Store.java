package ru.job4j.ood.parking;

public interface Store {

    boolean add(Vehicle vehicle);
    Vehicle remove(String key);

}
