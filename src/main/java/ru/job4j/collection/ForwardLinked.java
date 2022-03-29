package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T tmp = head.value;
        Node<T> next = head.next;
        head.next = null;
        head.value = null;
        head = next;
        return tmp;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> tmpPrev = null;
        Node<T> tmp = head;
        while (tmp != null) {
            Node<T> tmpNext = tmp.next;
            tmp.next = tmpPrev;
            tmpPrev = tmp;
            tmp = tmpNext;
            head = tmpPrev;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}