package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> log = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
        boolean pause = false;
        int index;
        List<String> answer = readPhrases();
        while (run) {
            String input = scanner.nextLine();
            if (OUT.equals(input)) {
                log.add("CMD: " + input);
                run = false;
            } else if (STOP.equals(input)) {
                log.add("CMD: " + input);
                pause = true;
            } else if (CONTINUE.equals(input)) {
                log.add("CMD: " + input);
                pause = false;
            } else {
                log.add("I: " + input);
                if (!pause) {
                    index = (int) (Math.random() * answer.size());
                    String out = "Bot: " + answer.get(index);
                    log.add(out);
                    System.out.println(out);
                }
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                rsl.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            for (String l : log) {
                out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chat_log.txt", "data/answers.txt");
        cc.run();
    }
}
