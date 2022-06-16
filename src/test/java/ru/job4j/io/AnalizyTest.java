package ru.job4j.io;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void example1() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String rsl;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(target))) {
            byte[] str = new byte[in.available()];
            in.read(str);
            rsl = new String(str);
        }
        assertThat(rsl, is("10:57:01;10:59:01;" + System.lineSeparator()
                + "11:01:02;11:02:02;" + System.lineSeparator()));
    }

    @Test
    public void example2() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String rsl;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(target))) {
            byte[] str = new byte[in.available()];
            in.read(str);
            rsl = new String(str);
        }
        assertThat(rsl, is("10:57:01;11:02:02;" + System.lineSeparator()));
    }

    @Test
    public void example3() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("500 11:02:02");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        String rsl;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(target))) {
            byte[] str = new byte[in.available()];
            in.read(str);
            rsl = new String(str);
        }
        assertThat(rsl, is("10:57:01;11:02:02;" + System.lineSeparator()));
    }
}