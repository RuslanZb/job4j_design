package ru.job4j.ood.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ControlQualityTest {
    private static ControlQuality controlQuality;
    private static List<Store> stores;

    @BeforeAll
    public static void init() {
        controlQuality = new ControlQuality(LocalDate.now());
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        warehouse.setLimitUp(25);
        shop.setLimitDown(25);
        shop.setLimitUp(75);
        stores = List.of(warehouse, shop, trash);
    }

    private static LocalDate minusDay(int day) {
        return LocalDate.now().minus(day, ChronoUnit.DAYS);
    }

    private static LocalDate plusDay(int day) {
        return LocalDate.now().plus(day, ChronoUnit.DAYS);
    }

    @Test
    public void whenAddToWarehouse() {
        Food milk = new Milk("Молоко", minusDay(1), plusDay(10), 100, 30);
        controlQuality.add(stores, milk);
        assertAll(
                () -> Assertions.assertTrue(stores.get(0).find(milk)),
                () -> assertThat(stores.get(1).findAll()).isEmpty(),
                () -> assertThat(stores.get(2).findAll()).isEmpty()
        );

    }

    @Test
    public void whenAddToShop() {
        Food meat = new Meat("Мясо", minusDay(5), plusDay(2), 1000, 100);
        controlQuality.add(stores, meat);
        assertAll(
                () -> assertThat(stores.get(0).findAll()).isEmpty(),
                () -> Assertions.assertTrue(stores.get(1).find(meat)),
                () -> assertThat(stores.get(2).findAll()).isEmpty()
        );

    }

    @Test
    public void whenAddToTrash() {
        Food bread = new Bread("Хлеб", minusDay(10), minusDay(1), 50, 10);
        controlQuality.add(stores, bread);
        assertAll(
                () -> assertThat(stores.get(0).findAll()).isEmpty(),
                () -> assertThat(stores.get(1).findAll()).isEmpty(),
                () -> Assertions.assertTrue(stores.get(2).find(bread))
        );
    }

    @Test
    public void whenAddToShopAndChangePrice() {
        Food meat = new Meat("Мясо", minusDay(20), plusDay(1), 1000, 100);
        controlQuality.add(stores, meat);
        assertAll(
                () -> assertThat(stores.get(0).findAll()).isEmpty(),
                () -> assertThat(stores.get(1).findAll().get(0).getPrice()).isEqualTo(900),
                () -> assertThat(stores.get(2).findAll()).isEmpty()
        );
    }

    @Test
    public void whenAddToStoresAndChangeCurrentDate() {
        Food milk = new Milk("Молоко", minusDay(1), plusDay(4), 100, 30);
        Food meat = new Meat("Мясо", minusDay(1), plusDay(3), 1000, 100);
        Food chicken = new Meat("Курица", minusDay(4), plusDay(3), 500, 100);
        Food bread = new Bread("Хлеб", minusDay(10), plusDay(1), 50, 10);
        controlQuality.add(stores, milk);
        controlQuality.add(stores, meat);
        controlQuality.add(stores, chicken);
        controlQuality.add(stores, bread);
        controlQuality.setCurrentDate(plusDay(2));
        controlQuality.rotation(stores);
        Food exceptBread = new Bread("Хлеб", minusDay(10), plusDay(1), 40, 10);
        assertAll(
                () -> assertThat(stores.get(0).findAll()).isEmpty(),
                () -> Assertions.assertTrue(stores.get(1).find(meat)),
                () -> Assertions.assertTrue(stores.get(1).find(milk)),
                () -> Assertions.assertTrue(stores.get(1).find(chicken)),
                () -> Assertions.assertTrue(stores.get(2).find(exceptBread))
        );
    }
}