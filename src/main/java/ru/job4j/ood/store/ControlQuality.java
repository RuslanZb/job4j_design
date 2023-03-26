package ru.job4j.ood.store;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private LocalDate currentDate;

    public ControlQuality(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void rotation(List<Store> stores) {
        for (Store store : stores) {
            for (Food food : store.replace(currentDate)) {
                add(stores, food);
            }
        }
    }

    public void add(List<Store> stores, Food food) {
        for (Store store : stores) {
            if (store.add(food, currentDate)) {
                break;
            }
        }
    }
}
