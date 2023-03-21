package ru.job4j.gc;

import java.lang.ref.SoftReference;

public class Reference {

    private static final String STRING = "Soft Reference";

    public static void main(String[] args) {
        SoftReference<String> stringSoftReference = new SoftReference<>(STRING);
        String string = stringSoftReference.get();
        if (string == null) {
            stringSoftReference = new SoftReference<>(STRING);
            string = stringSoftReference.get();
        }
        System.out.println(string);
        System.out.println(stringSoftReference);
    }
}
