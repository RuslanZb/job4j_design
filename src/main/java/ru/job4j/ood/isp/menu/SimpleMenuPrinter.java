package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        String indent = "----";
        while (iterator.hasNext()) {
            StringBuilder line = new StringBuilder();
            Menu.MenuItemInfo menuItemInfo = iterator.next();
            int deep = menuItemInfo.getNumber().split("\\.").length;
            line.append(indent.repeat(deep - 1));
            line.append(menuItemInfo.getNumber()).append(menuItemInfo.getName());
            System.out.println(line);
        }
    }
}
