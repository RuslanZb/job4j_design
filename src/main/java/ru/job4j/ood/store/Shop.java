package ru.job4j.ood.store;

import java.time.LocalDate;

public class Shop extends AbstractStore {


    @Override
    public boolean predicate(Food food, LocalDate currentDate) {
        double calc = calculateExpiration(food, currentDate);
        return calc >= limitDown / 100 && calc <= 1;
    }

    @Override
    public void update(Food food, LocalDate currentDate) {
        double calc = calculateExpiration(food, currentDate);
        if (calc > limitUp / 100 && calc <= 1) {
            double newPrice = food.getPrice() - food.getDiscount();
            food.setPrice(newPrice);
        }
    }
}


