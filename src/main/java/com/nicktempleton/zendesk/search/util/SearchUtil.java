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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SearchUtil {

    /**
     * Search the given list of data for items that have the given search field
     * and it's value matches (or contains in the case of lists) the given
     * search value.
     * 
     * @param data List of data items (maps) to search, the keys of the maps
     *             correspond to the search field
     * @param searchField Field/key within the maps to match upon
     * @param searchValue Value to look for in the search field
     * @return List of data items that contain a field with a name of the
     *         search field and have a value that matches the search value
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> search(List<Map<String, Object>> data, String searchField, String searchValue) {
        List<Map<String, Object>> results = new ArrayList<>();

        if (null == data) {
            return results;
        }

        for (Map<String, Object> listItem : data) {
            Object fieldValue = listItem.get(searchField);

            // Let's consider a null value to simply be empty
            if (null == fieldValue) {
                fieldValue = "";
            }

            if (fieldValue instanceof Double) {
                // Gson treats numbers as doubles, but we want to be able to
                // enter integers, so we do some conversion of the input
                try {
                    if (fieldValue.equals(Double.valueOf(searchValue))) {
                        results.add(listItem);
                    }
                } catch (NumberFormatException nfe) {
                    // move on, the input wasn't a number
                }
            } else if (fieldValue instanceof List) {
                // arrays are assumed to only contain strings
                if (((List<String>)fieldValue).contains(searchValue)) {
                    results.add(listItem);
                }
            } else if (fieldValue instanceof Object) {
                // most fields can simply be converted to a string and compared
                if (fieldValue.toString().equals(searchValue)) {
                    results.add(listItem);
                }
            }
        }

        return results;
    }

    /**
     * Get a set of all the possible searchable fields from the given data.
     * 
     * @param data List of data items that have searchable fields/keys
     * @return a set of all the possible searchable fields in the data
     */
    public static Set<String> searchableFields(List<Map<String, Object>> data) {
        Set<String> searchableFields = new TreeSet<>();

        if (null == data) {
            return searchableFields;
        }

        for (Map<String, Object> org : data) {
            searchableFields.addAll(org.keySet());
        }
        return searchableFields;
    }
}
