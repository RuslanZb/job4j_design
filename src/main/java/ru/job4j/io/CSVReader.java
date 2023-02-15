package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    private static List<List<String>> source;
    private static List<List<String>> result;

    public static void handle(ArgsName argsName) {
        source = new ArrayList<>();
        result = new ArrayList<>();
        exception(argsName);
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(System.lineSeparator())) {
            while (scanner.hasNextLine()) {
                List<String> line = new ArrayList<>(Arrays.asList(scanner.next()
                        .split(argsName.get("delimiter"))));
                source.add(line);
            }
            filter(argsName.get("filter").split(","), argsName.get("delimiter"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("stdout".equals(argsName.get("out"))) {
            PrintStream out = new PrintStream(System.out, true);
            output(out);
        } else {
            try (PrintStream out = new PrintStream(
                    new BufferedOutputStream(
                            new FileOutputStream(argsName.get("out")))
            )) {
                output(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void filter(String[] values, String delimiter) {
        for (List<String> l : source) {
            List<String> line = new ArrayList<>();
            int count = 0;
            for (String value : values) {
                if (source.get(0).contains(value)) {
                    int i = source.get(0).indexOf(value);
                    if (count != values.length - 1) {
                        line.add(l.get(i) + delimiter);
                    } else {
                        line.add(l.get(i));
                    }
                    count++;
                }
            }
            result.add(line);
        }
    }

    private static void output(PrintStream out) {
        for (List<String> line : result) {
            for (String l : line) {
                out.print(l);
            }
            out.println();
        }
    }

    private static void exception(ArgsName args) {
        if (args.get("path").isEmpty() || args.get("out").isEmpty()) {
            throw new IllegalArgumentException("Source file or out parameter are null.");
        }
        if (!Path.of(args.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("Source file doesn't exist.");
        }
        if (!String.valueOf(args.get("path")).endsWith(".csv")) {
            throw new IllegalArgumentException("File extension is incorrect.");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
