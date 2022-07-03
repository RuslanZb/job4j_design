package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        print(duplicatesVisitor);

    }

    private static void print(DuplicatesVisitor duplicates) {
        for (Map.Entry<FileProperty, List<Path>> map : duplicates.getFiles().entrySet()) {
            if (map.getValue().size() > 1) {
                System.out.printf("%s - %d bytes:" + System.lineSeparator(),
                        map.getKey().getName(),
                        map.getKey().getSize());
                for (Path path : map.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }
}