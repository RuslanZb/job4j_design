package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        exception(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void exception(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null or missing search extension.");
        }
        if (!Path.of(args[0]).toFile().exists()) {
            throw new IllegalArgumentException("Root folder doesn't exist.");
        }
        if (!String.valueOf(args[1]).startsWith(".")) {
            throw new IllegalArgumentException("File extension is incorrect.");
        }
    }
}
