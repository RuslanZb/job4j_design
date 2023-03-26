package ru.job4j.ood.store;

import java.time.LocalDate;

public class Warehouse extends AbstractStore {


    @Override
    public boolean predicate(Food food, LocalDate currentDate) {
        return calculateExpiration(food, currentDate) < limitUp / 100;
    }

    @Override
    public void update(Food food, LocalDate currentDate) {

    }
}
