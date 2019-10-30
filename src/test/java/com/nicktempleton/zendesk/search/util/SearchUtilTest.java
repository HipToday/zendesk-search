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
package com.nicktempleton.zendesk.search.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SearchUtilTest {

    private Map<String, Object> stringValue = Collections.singletonMap("string", "string value");
    private Map<String, Object> anotherString = Collections.singletonMap("string", "string value");
    private Map<String, Object> doubleValue = Collections.singletonMap("double", new Double(1.0));
    private Map<String, Object> stringList = Collections.singletonMap("stringList", Arrays.asList( "string", "list" ));
    private Map<String, Object> nullValue = Collections.singletonMap("null", null);
    private Map<String, Object> booleanValue = Collections.singletonMap("boolean", new Boolean(true));

    private List<Map<String, Object>> data = Arrays.asList(
        stringValue,
        anotherString,
        doubleValue,
        stringList,
        nullValue,
        booleanValue
    );

    @Test
    public void testSearchForString() {
        assertEquals(2, SearchUtil.search(data, "string", "string value").size());
        assertEquals(0, SearchUtil.search(data, "string", "wrong value").size());
    }

    @Test
    public void testSearchForStringList() {
        assertEquals(1, SearchUtil.search(data, "stringList", "string").size());
        assertEquals(1, SearchUtil.search(data, "stringList", "list").size());
        assertEquals(0, SearchUtil.search(data, "stringList", "string list").size());
    }

    @Test
    public void testSearchForNumber() {
        assertEquals(1, SearchUtil.search(data, "double", "1").size());
        assertEquals(1, SearchUtil.search(data, "double", "1.0").size());
        assertEquals(0, SearchUtil.search(data, "double", "10").size());
    }

    @Test
    public void testSearchForBoolean() {
        assertEquals(1, SearchUtil.search(data, "boolean", "true").size());
        assertEquals(0, SearchUtil.search(data, "boolean", "false").size());
    }

    @Test
    public void testSearchForEmpty() {
        assertEquals(6, SearchUtil.search(data, "null", "").size());
    }

    @Test
    public void testSearchWithNullData() {
        assertEquals(0, SearchUtil.search(null, "null", "").size());
        assertEquals(6, SearchUtil.search(data, null, "").size());
        assertEquals(0, SearchUtil.search(data, "null", null).size());
    }
}
