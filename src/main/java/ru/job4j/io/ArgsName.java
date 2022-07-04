package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        checkGet(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        check(args);
        for (String arg : args) {
            this.values.put(arg.substring(1, arg.indexOf("=")),
                    arg.substring(arg.indexOf("=") + 1));
        }
    }

    private void check(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args is empty.");
        }
        for (String arg : args) {
            if (!arg.startsWith("-") || arg.startsWith("=") || !arg.contains("=")
                    || arg.indexOf("=") == 1
                    || arg.indexOf("=") == arg.length() - 1) {
                throw new IllegalArgumentException("Incorrect pair \"-key=value\".");
            }
        }
    }

    private void checkGet(String key) {
        if (!this.values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key \"%s\" does not exist", key));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }


}