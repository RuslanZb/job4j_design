package ru.job4j.ood.parking;

import java.util.Objects;

public abstract class Vehicle {
    private String number;
    private int size;

    public Vehicle(String number, int size) {
        this.number = number;
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(number, vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
