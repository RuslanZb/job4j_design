package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenWithComment() {
        String path = "./data/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("country"));
        assertThat(config.value("city"), is("slv"));
    }

    @Test
    public void whenEmptyLine() {
        String path = "./data/empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("country"), is("ru"));
        assertThat(config.value("city"), is("slv"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotKey() {
        String path = "./data/notkey.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotValue() {
        String path = "./data/notvalue.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotKeyAndNotValue() {
        String path = "./data/NotKeyAndNotValue.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotEqualsSign() {
        String path = "./data/NotEqualsSign.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenDoubleEqualsSign() {
        String path = "./data/DoubleEqualsSign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("country"), is("ru=a"));
        assertThat(config.value("city"), is("slv="));
    }

    @Test
    public void whenEmptyFile() {
        String path = "./data/EmptyFile.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("country"));
    }

    @Test
    public void whenSharpInKey() {
        String path = "./data/SharpInKey.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("country"));
        assertThat(config.value("country#"), is("ru"));
    }
}