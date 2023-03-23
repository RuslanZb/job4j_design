package ru.job4j.ood.srp;

public interface Taxi<T> {
    T getRoute(T depAddress, T arrAddress);

    double calculatePrice(T route, int category);
}
