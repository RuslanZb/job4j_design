package ru.job4j.ood.isp;

public interface Device {
    void print();

    void scan();
}

class Printer implements Device {

    @Override
    public void print() {
        System.out.println("Печатает");
    }

    @Override
    public void scan() {

    }
}
