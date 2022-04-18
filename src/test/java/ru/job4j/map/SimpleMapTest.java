package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {

    @Test
    public void whenPutAndGetThenTrue() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        Assert.assertEquals("one", map.get(1));
    }

    @Test
    public void whenPutThenFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertFalse(map.put(1, "two"));
    }

    @Test
    public void whenGetThenNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        Assert.assertNull(map.get(2));
    }

    @Test
    public void whenPutNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "one");
        Assert.assertEquals("one", map.get(null));
    }

    @Test
    public void whenPutTwoNullThenFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "one");
        assertFalse(map.put(null, "two"));
    }

    @Test
    public void whenRemoveThenTrue() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertTrue(map.remove(1));
        Assert.assertNull(map.get(1));
    }

    @Test
    public void whenRemoveThenFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertFalse(map.remove(3));
    }

    @Test
    public void whenExpend() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "eee");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(7, "7");
        map.put(8, "8");
        Assert.assertEquals("8", map.get(8));
    }

    @Test
    public void whenCheckIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "eee");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenEmptyCellThenIteratorSkip() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "eee");
        map.put(5, "5");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(5), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(1, "one");
        iterator.next();
    }
}