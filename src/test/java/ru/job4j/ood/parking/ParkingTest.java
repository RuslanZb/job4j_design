package ru.job4j.ood.parking;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Ignore
class ParkingTest {

    @Test
    public void whenAddCar() {
        Store parking = new Parking(1, 1);
        Vehicle vehicle = new Car("777", 1);
        Assertions.assertTrue(parking.add(vehicle));
    }

    @Test
    public void whenRemoveCar() {
        Store parking = new Parking(1, 1);
        Vehicle vehicle = new Car("777", 1);
        parking.add(vehicle);
        assertThat(parking.remove("777")).isEqualTo(vehicle);
    }

    @Test
    public void whenAddTruck() {
        Store parking = new Parking(1, 1);
        Vehicle vehicle = new Truck("777", 2);
        Assertions.assertTrue(parking.add(vehicle));
    }

    @Test
    public void whenRemoveTruck() {
        Store parking = new Parking(1, 1);
        Vehicle vehicle = new Truck("777", 2);
        parking.add(vehicle);
        assertThat(parking.remove("777")).isEqualTo(vehicle);
    }

    @Test
    public void whenAddTruckButNotPlace() {
        Store parking = new Parking(1, 0);
        Vehicle vehicle = new Truck("777", 2);
        Assertions.assertFalse(parking.add(vehicle));
    }

    @Test
    public void whenAddCarButPlaceIsBusy() {
        Store parking = new Parking(1, 1);
        Vehicle vehicle = new Car("777", 1);
        Vehicle car = new Car("666", 1);
        parking.add(vehicle);
        Assertions.assertFalse(parking.add(car));
    }

    @Test
    public void whenAddTruckThenAddedToCarPlace() {
        Store parking = new Parking(2, 0);
        Vehicle vehicle = new Truck("777", 2);
        Assertions.assertTrue(parking.add(vehicle));
    }

    @Test
    public void whenAddCarsAndRemoveCarFromEdgesAndAddTruckThenDoesNotAdded() {
        Store parking = new Parking(3, 0);
        Vehicle car1 = new Car("111", 1);
        Vehicle car2 = new Car("222", 1);
        Vehicle car3 = new Car("333", 1);
        Vehicle truck = new Truck("777", 2);
        parking.add(car1);
        parking.add(car2);
        parking.add(car3);
        parking.remove("111");
        parking.remove("333");
        Assertions.assertFalse(parking.add(truck));
    }

}