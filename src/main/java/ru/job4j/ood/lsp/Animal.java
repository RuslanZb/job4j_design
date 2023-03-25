package ru.job4j.ood.lsp;

public class Animal {
    protected int leg;
    protected boolean larynx;
    protected int age;

    public Animal(int leg, boolean larynx, int age) {
        this.leg = leg;
        this.larynx = larynx;
        this.age = age;
    }

    public void run(String string) {
        if (leg < 2) {
            System.out.println("Не может бежать");
        }
    }

    public String sound(String sound) {
        String rsl = "";
        if (larynx) {
            rsl = sound;
        }
        return sound;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст меньше 0");
        }
        this.age = age;
    }
}

class Dog extends Animal {

    public Dog(int leg, boolean larynx, int age) {
        super(leg, larynx, age);
    }

    @Override
    public void run(String string) {
        if (leg < 4) {
            System.out.println("Не может бежать");
        }
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}

class Fish extends Animal {

    public Fish(int leg, boolean larynx, int age) {
        super(leg, larynx, age);
    }

    @Override
    public String sound(String sound) {
        return sound;
    }
}

