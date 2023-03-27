package ru.job4j.ood.isp;

public interface Vehicle {

    void pumpUpWheel();
    void changeOil();
    void refuel();

}

class Bicycle implements Vehicle {

    @Override
    public void pumpUpWheel() {
        System.out.println("Подкачать колесо");
    }

    @Override
    public void changeOil() {

    }

    @Override
    public void refuel() {

    }
}
