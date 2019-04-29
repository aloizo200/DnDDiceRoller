package com.example.dnddiceroller;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public DummyClass dr = new DummyClass();

    @Test
    public void addition_isCorrect() {
        List<Integer> results = dr.randomizer("Roll the dice 4");
        assertEquals(1, results.size());
        assertTrue(results.get(0) <= 4 && results.get(0)>= 1);
        results = dr.randomizer("Roll the dice 6 times 5");
        assertEquals(5, results.size());
        assertTrue(results.get(3) <= 6 && results.get(3)>= 1);
        results = dr.randomizer("Roll the dice 46");
        assertEquals(0, results.size());
        results = dr.randomizer("Roll the dice 8 x 5");
        assertEquals(5, results.size());
        assertTrue(results.get(3) <= 8 && results.get(3)>= 1);
    }
}