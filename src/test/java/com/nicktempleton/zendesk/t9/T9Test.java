/*
 * Copyright (c) 2019 Nick Templeton
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.nicktempleton.zendesk.t9;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class T9Test {

    @Test
    public void testZeroElements() {
        String[] expected = {};
        Integer[] keys = {};
        assertArrayEquals(expected, translate(keys));
    }

    @Test
    public void testSingleElement() {
        String[] expected = { "a", "b", "c" };
        Integer[] keys = { 2 };
        assertArrayEquals(expected, translate(keys));
    }

    @Test
    public void testTwoElements() {
        String[] expected = { "ap", "bp", "cp", "aq", "bq", "cq", "ar", "br", "cr", "as", "bs", "cs" };
        Integer[] keys = { 2, 7 };
        assertArrayEquals(expected, translate(keys));
    }

    @Test
    public void testThreeElements() {
        String[] expected = {
            "aaa", "baa", "caa",
            "aba", "bba", "cba",
            "aca", "bca", "cca",
            "aab", "bab", "cab",
            "abb", "bbb", "cbb",
            "acb", "bcb", "ccb",
            "aac", "bac", "cac",
            "abc", "bbc", "cbc",
            "acc", "bcc", "ccc" };
        Integer[] keys = { 2, 2, 2 };
        assertArrayEquals(expected, translate(keys));
    }

    @Test
    public void testSpaceElements() {
        String[] expected = { "  " };
        Integer[] keys = { 0, 0 };
        assertArrayEquals(expected, translate(keys));
    }

    @Test
    public void testComboElements() {
        String[] expected = { " a", " b", " c" };
        Integer[] keys = { 0, 1, 2 };
        assertArrayEquals(expected, translate(keys));
    }

    String[][] LETTERS = {
        { " " },
        { "" },
        { "a", "b", "c" },
        { "d", "e", "f" },
        { "g", "h", "i" },
        { "j", "k", "l" },
        { "m", "n", "o" },
        { "p", "q", "r", "s" },
        { "t", "u", "v" },
        { "w", "x", "y", "z" }
    };

    private String[] translate(Integer[] keys) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < keys.length; i++) {
            String[] newchars = LETTERS[keys[i]];
            if (i == 0) {
                result = new ArrayList<>(Arrays.asList(newchars));
            } else {
                ArrayList<String> expander = new ArrayList<>();
                for (int j = 0; j < newchars.length; j++) {
                    expander.addAll(result);
                    for (int k = 0; k < result.size(); k++) {
                        int index = k + (j * result.size());
                        expander.set(index, expander.get(index) + newchars[j]);
                    }
                }

                result = expander;
            }
        }

        System.out.println(result);
        return result.toArray(new String[result.size()]);
    }
}
