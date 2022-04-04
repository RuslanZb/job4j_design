package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Гарри", 1, new GregorianCalendar(1980, Calendar.AUGUST, 31));
        User user2 = new User("Гарри", 1, new GregorianCalendar(1980, Calendar.AUGUST, 31));
        Map<User, Object> users = new HashMap<>();
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users);
    }
}
