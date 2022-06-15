package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> log = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String start = null;
            String finish = null;
            String tmp = null;
            boolean flag = false;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] lineArray = line.split(" ");
                if (("400".equals(lineArray[0]) || "500".equals(lineArray[0])) && !flag) {
                    flag = true;
                    start = lineArray[1];
                }
                if (!("400".equals(lineArray[0]) || "500".equals(lineArray[0])) && flag) {
                    flag = false;
                    finish = lineArray[1];
                    log.add(start + ";" + finish + ";");
                }
                tmp = lineArray[1];
            }
            if (flag) {
                log.add(start + ";" + tmp + ";");
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String l : log) {
                out.println(l);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server_example1.log", "./data/example1.log");
        analizy.unavailable("./data/server_example2.log", "./data/example2.log");
        analizy.unavailable("./data/server_example3.log", "./data/example3.log");
    }
}