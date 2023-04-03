package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;

public class Car {
    private static Map<Integer, String> engine = new HashMap<>();

    public static void main(String[] args) {
        engine.put(1, "String");
    }
}
