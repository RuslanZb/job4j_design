package ru.job4j.ood.srp;

public interface Birds<T> {
    T fly(String... parameters);

    T eat(String... parameters);
}
