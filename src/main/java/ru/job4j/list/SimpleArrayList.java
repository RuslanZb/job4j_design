package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        extension();
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T temp = container[index];
        container[index] = newValue;
        modCount++;
        return temp;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T temp = null;
        if (size > index) {
            temp = container[index];
            System.arraycopy(container, index + 1, container, index, size - 1 - index);
            container[size - 1] = null;
            size--;
            modCount++;
        }
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[index++];
            }

        };
    }

    private void extension() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }
}
