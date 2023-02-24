package ru.job4j.ioexam;

import ru.job4j.io.ArgsName;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class SearchFile {
    private static void validate(ArgsName args) {
        if (args.size() < 4) {
            throw new IllegalArgumentException("One or more parameters don't set.");
        }
        if (!Path.of(args.get("d")).toFile().exists()) {
            throw new IllegalArgumentException("Folder doesn't exist.");
        }
        String type = args.get("t");
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("The search type \"-t\" must be \"mask\", \"name\" or \"regex\"");
        }
        if (!"regex".equals(type) && !args.get("n").contains(".")
                || "regex".equals(type) && !args.get("n").contains("\\.")) {
            throw new IllegalArgumentException("File name is incorrect.");
        }

    }

    public static List<Path> search(Path path, String type, String key) throws IOException {
        Pattern pattern = switch (type) {
            case "name" -> Pattern.compile(key, Pattern.LITERAL);
            case "mask" -> Pattern.compile(key.replace(".", "\\.")
                    .replace("*", ".*")
                    .replace("?", ".{1}"));
            case "regex" -> Pattern.compile(key);
            default -> null;
        };
        Searcher searcher = new Searcher(pattern);
        Files.walkFileTree(path, searcher);
        return searcher.getPaths();
    }

    public static void save(List<Path> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (Path l : log) {
                out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName parameters = ArgsName.of(args);
        validate(parameters);
        Path path = Paths.get(parameters.get("d"));
        save(search(path, parameters.get("t"), parameters.get("n")),
                parameters.get("o"));
    }


}
