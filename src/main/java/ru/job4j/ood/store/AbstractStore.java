package ru.job4j.ood.store;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store {
    public List<Food> foods = new ArrayList<>();
    public float limitUp;
    public float limitDown;


    @Override
    public boolean add(Food food, LocalDate currentDate) {
        update(food, currentDate);
        boolean rsl = predicate(food, currentDate);
        if (rsl) {
            foods.add(food);
        }
        return rsl;
    }

    @Override
    public boolean find(Food food) {
        return foods.contains(food);
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(foods);
    }

    @Override
    public Food remove(Food food) {
        foods.remove(food);
        return food;
    }

    @Override
    public List<Food> replace(LocalDate currentDate) {
        Predicate<Food> predicate = food -> !predicate(food, currentDate);
        List<Food> removed = foods.stream().filter(predicate).collect(Collectors.toList());
        foods.removeAll(removed);
        foods.forEach(f -> update(f, currentDate));
        return removed;
    }

    @Override
    public void setLimitUp(float limitUp) {
        this.limitUp = limitUp;
    }

    @Override
    public void setLimitDown(float limitDown) {
        this.limitDown = limitDown;
    }

    public abstract boolean predicate(Food food, LocalDate currentDate);

    public abstract void update(Food food, LocalDate currentDate);

    public double calculateExpiration(Food food, LocalDate currentDate) {
        return 1 - ((double) Period.between(food.getCreateDate(), currentDate).get(ChronoUnit.DAYS)
                / (double) Period.between(food.getCreateDate(), food.getExpiryDate()).get(ChronoUnit.DAYS));
    }
}
