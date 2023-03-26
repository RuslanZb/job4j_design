package ru.job4j.ood.store;

import java.time.LocalDate;

public class Trash extends AbstractStore {

    @Override
    public boolean predicate(Food food, LocalDate currentDate) {
        return calculateExpiration(food, currentDate) > 1;
    }

    @Override
    public void update(Food food, LocalDate currentDate) {

    }
}
