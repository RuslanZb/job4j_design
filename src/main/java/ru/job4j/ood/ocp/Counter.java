package ru.job4j.ood.ocp;

public class Counter {
    private static int count;

    public static int count(boolean pulse) {
        if (pulse) {
            count++;
        }
        return count;
    }
}
