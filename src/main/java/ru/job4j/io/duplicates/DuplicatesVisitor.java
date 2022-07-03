package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (!files.containsKey(fileProperty)) {
            files.put(fileProperty, new ArrayList<>());
        }
        files.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getFiles() {
        return files;
    }
}