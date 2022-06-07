package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String num : text.toString().split(System.lineSeparator())) {
                String rsl = "нечётное";
                if (Integer.parseInt(num) % 2 == 0) {
                    rsl = "чётное";
                }
                System.out.println(num + " - " + rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}