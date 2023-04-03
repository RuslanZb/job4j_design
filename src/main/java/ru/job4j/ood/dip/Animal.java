package ru.job4j.ood.dip;

public interface Animal {
    void run();
}

class Cat implements Animal {

    @Override
    public void run() {
        System.out.println("Бежит");
    }
}

class Game {
    public static Cat cat = new Cat();

    public static void main(String[] args) {
        cat.run();
    }
}
