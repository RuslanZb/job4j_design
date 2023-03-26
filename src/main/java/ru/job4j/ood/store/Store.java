package ru.job4j.ood.store;

import java.time.LocalDate;
import java.util.List;

public interface Store {
    boolean add(Food food, LocalDate currentDate);

    boolean find(Food food);

    List<Food> findAll();

    Food remove(Food food);

    List<Food> replace(LocalDate currentDate);

    void setLimitUp(float limitUp);

    void setLimitDown(float limitDown);

}
