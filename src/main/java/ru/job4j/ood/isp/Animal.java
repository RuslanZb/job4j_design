package ru.job4j.ood.isp;

public interface Animal {
    void run();
    void swim();
    void fly();
}

class Fish implements Animal {

    @Override
    public void run() {

    }

    @Override
    public void swim() {
        System.out.println("Плывет");
    }

    @Override
    public void fly() {

    }
}
