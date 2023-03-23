package ru.job4j.ood.ocp;

public class Birds {


    private static class Sparrow {
        public void action() {
            System.out.println("Летит");
        }
    }

    public static void main(String[] args) {
        Sparrow sparrow = new Sparrow();
        sparrow.action();
    }
}
