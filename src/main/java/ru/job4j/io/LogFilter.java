package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] lineArray = line.split(" ");
                if ("404".equals(lineArray[lineArray.length - 2])) {
                    rsl.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String l : log) {
            System.out.println(l);
        }


    }
}