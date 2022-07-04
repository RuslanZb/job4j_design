package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String[] argsValue = new String[]{
                ArgsName.of(args).get("d"),
                ArgsName.of(args).get("e"),
                ArgsName.of(args).get("o")
        };
        check(argsValue);
        Zip zip = new Zip();
        List<Path> sources = Search.search(Paths.get(argsValue[0]),
                p -> !p.toFile().getName().endsWith(argsValue[1]));
        zip.packFiles(sources, new File(argsValue[2]));
    }

    private static void check(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Missing input arguments.");
        }
        if (!Path.of(args[0]).toFile().exists()) {
            throw new IllegalArgumentException("Root folder doesn't exist.");
        }
        if (!String.valueOf(args[1]).startsWith(".")) {
            throw new IllegalArgumentException("Extension of excluded files is incorrect.");
        }
        if (!String.valueOf(args[2]).endsWith(".zip")) {
            throw new IllegalArgumentException("Archive extension is incorrect.");
        }
    }
}