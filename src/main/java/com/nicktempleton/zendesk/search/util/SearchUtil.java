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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchUtil {

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> search(List<Map<String, Object>> data, String searchField, String searchValue) {
        List<Map<String, Object>> results = new ArrayList<>();

        for (Map<String, Object> listItem : data) {
            Object fieldValue = listItem.get(searchField);
            System.out.println(null != fieldValue ? fieldValue.getClass() : "null");

            // Let's consider a null value to simply be empty
            if (null == fieldValue) {
                fieldValue = "";
            }

            if (fieldValue instanceof Double) {
                try {
                    if (fieldValue.equals(Double.valueOf(searchValue))) {
                        results.add(listItem);
                    }
                } catch (NumberFormatException nfe) {
                    // move on
                }
            } else if (fieldValue instanceof ArrayList) {
                if (((ArrayList<String>)fieldValue).contains(searchValue)) {
                    results.add(listItem);
                }
            } else if (fieldValue instanceof Object) {
                if (fieldValue.toString().equals(searchValue)) {
                    results.add(listItem);
                }
            }
        }

        results.forEach(System.out::println);
        return results;
    }

    public static Set<String> searchableFields(List<Map<String, Object>> data) {
        Set<String> searchableFields = new HashSet<>();
        for (Map<String, Object> org : data) {
            searchableFields.addAll(org.keySet());
        }
        return searchableFields;
    }
}
