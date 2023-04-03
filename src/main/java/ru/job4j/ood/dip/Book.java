package ru.job4j.ood.dip;

public class Book {
    private String text;

    public Book(String text) {
        this.text = text;
    }

    public void print() {
        ConsolePrinter printer = new ConsolePrinter();
        printer.print(text);
    }
}

class ConsolePrinter {
    public void print(String text) {
        System.out.println(text);
    }
}
