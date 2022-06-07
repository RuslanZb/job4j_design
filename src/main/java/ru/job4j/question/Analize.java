package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int ch = 0;
        int del = previous.size();
        Map<Integer, String> previousMap = new HashMap<>();
        for (User prev : previous) {
            previousMap.put(prev.getId(), prev.getName());
        }
        for (User curr : current) {
            if (!previousMap.containsKey(curr.getId()) && !previousMap.containsValue(curr.getName())) {
                add++;
            }
            if (previousMap.containsKey(curr.getId()) && !previousMap.containsValue(curr.getName())) {
                ch++;
            }
            if (previousMap.containsKey(curr.getId())) {
                del--;
            }
        }
        return new Info(add, ch, del);
    }
}