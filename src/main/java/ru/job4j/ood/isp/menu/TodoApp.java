package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {

    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        MenuPrinter printer = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        while (run) {
            System.out.println("""
                        Введите 0 для добаления нового пункта в меню.
                        Введите 1 для вывода меню.
                        Введите название действия для его вызова.
                        Для выхода введите -1.
                    """);
            String userChoice = scanner.nextLine();
            if ("-1".equals(userChoice)) {
                run = false;
            } else if ("1".equals(userChoice)) {
                printer.print(menu);
            } else if ("0".equals(userChoice)) {
                System.out.println("Введите родительский элемент меню или оставьте пустым, "
                       + "если хотите добавить элемент в корень:");
                String parent = scanner.nextLine();
                if (parent.isEmpty()) {
                    parent = null;
                }
                System.out.println("Введите название нового элемента:");
                String name = scanner.nextLine();
                menu.add(parent, name, DEFAULT_ACTION);
            } else {
                menu.select(userChoice).ifPresent(item -> item.getActionDelegate().delegate());
            }
        }
    }
}
