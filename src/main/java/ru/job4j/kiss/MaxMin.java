package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return sort(value, comparator).get(value.size() - 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return sort(value, comparator).get(0);
    }

    private static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Data is empty");
        }
        int low = 0;
        int high = list.size() - 1;
        if (low >= high) {
            return list;
        }
        int mid = high / 2;
        T border = list.get(mid);
        int i = low;
        int j = high;
        while (i <= j) {
            while (comparator.compare(list.get(i), border) < 0) {
                i++;
            }
            while (comparator.compare(list.get(j), border) > 0) {
                j--;
            }
            if (i <= j) {
                T swap =  list.set(i, list.get(j));
                list.set(j, swap);
                i++;
                j--;
            }
        }
        if (low <= j) {
            sort(list.subList(low, j + 1), comparator);
        }
        if (high >= i) {
            sort(list.subList(i, high + 1), comparator);
        }
        return list;
    }
}

