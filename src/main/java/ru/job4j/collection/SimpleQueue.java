package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;
    private boolean sel = true;

    public T poll() {
        size--;
        if (!sel) {
            return in.pop();
        }
        return out.pop();
    }

    public void push(T value) {
        if (sel) {
            in.push(value);
            for (int i = 0; i < size; i++) {
                in.push(out.pop());
            }
            sel = false;
        } else {
            out.push(value);
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
            }
            sel = true;
        }
        size++;
    }

}