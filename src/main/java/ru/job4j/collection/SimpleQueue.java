package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;

    public T poll() {
        if (sizeOut == 0) {
            for (int i = 0; i < sizeIn - 1; i++) {
                out.push(in.pop());
                sizeOut++;
                sizeIn--;
            }
            sizeIn--;
            return in.pop();
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }

}