package ru.job4j.it;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveNegative() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, -1, 1, 2, 3, -3));
        ListUtils.removeIf(input, x -> x < 0);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenNotRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, -1, 1, 2, 3, -3));
        ListUtils.removeIf(input, x -> x < -5);
        assertThat(input, is(Arrays.asList(0, -1, 1, 2, 3, -3)));
    }

    @Test
    public void whenReplaceNullWithZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(null, 1, null, 3, null));
        ListUtils.replaceIf(input, Objects::isNull, 0);
        assertThat(input, is(Arrays.asList(0, 1, 0, 3, 0)));
    }

    @Test
    public void whenNotReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, x -> x == 0, 1);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void whenRemoveListWithOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        List<Integer> remove = new ArrayList<>(Arrays.asList(1, 3, 5));
        ListUtils.removeAll(input, remove);
        assertThat(input, is(Arrays.asList(0, 2, 4, 6)));
    }
}