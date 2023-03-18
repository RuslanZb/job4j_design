package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        exception(key);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(String.format("%s%s", cachingDir, key)))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = stringBuilder.toString();
        put(key, text);
        return text;
    }

    private static void exception(String key) {
        if (!Path.of(key).toFile().exists()) {
            throw new IllegalArgumentException("File doesn't exist.");
        }
    }
}