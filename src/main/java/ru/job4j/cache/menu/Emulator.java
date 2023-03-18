package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    private static DirFileCache dirFileCache;

    public static void main(String[] args) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println("""
                        Введите 1 для указания директории.
                        Введите 2 для указания файла.
                        Введите любое другое число для выхода.
                    """);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (1 == userChoice) {
                System.out.println("Введите директорию:");
                String text = scanner.nextLine();
                exception(text);
                dirFileCache = new DirFileCache(text);
            } else if (2 == userChoice) {
                System.out.println("Укажите название файла:");
                String text = scanner.nextLine();
                if (dirFileCache != null) {
                    System.out.println(dirFileCache.get(text));
                } else {
                    System.out.println("Не указана директория. Вначале укажите директорию");
                }
            } else {
                run = false;
            }
        }
    }

    private static void exception(String path) {
        if (!Path.of(path).toFile().exists()) {
            throw new IllegalArgumentException("Directory doesn't exist.");
        }
    }
}
