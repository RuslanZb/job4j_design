package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        size--;
        return in.pop();
    }

    public void push(T value) {
        if (size == 0) {
            in.push(value);
        } else {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
            }
            in.push(value);
            for (int i = 0; i < size; i++) {
                in.push(out.pop());
            }
        }
        size++;
    }

}