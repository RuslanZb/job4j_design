package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    public void when42181ThenMax8() {
        List<Integer> array = List.of(4, 2, 1, 8, 1);
        List<Integer> list = new ArrayList<>(array);
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(list, Comparator.naturalOrder())).isEqualTo(8);
    }

    @Test
    public void when42181ThenMin1() {
        List<Integer> array = List.of(4, 2, 1, 8, 1);
        List<Integer> list = new ArrayList<>(array);
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(list, Comparator.naturalOrder())).isEqualTo(1);
    }

    @Test
    public void whenEmpty() {
        List<Integer> list = new ArrayList<>();
        MaxMin maxMin = new MaxMin();
        assertThatThrownBy(() -> maxMin.max(list, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}