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
package com.nicktempleton.zendesk.search.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nicktempleton.zendesk.search.util.ResourceLoader;
import com.nicktempleton.zendesk.search.util.SearchUtil;

/**
 * Model object for our search data.
 */
public class SearchData {

    private List<Map<String, Object>> data;
    private Set<String> searchFields;

    /**
     * Construct search data from the given resource, a JSON file in the
     * resources directory.
     * 
     * @param resource Name of a file in the resources directory
     */
    public SearchData(String resource) {
        this.data = ResourceLoader.loadFromJsonResource(resource);
        this.searchFields = SearchUtil.searchableFields(data);
    }

    /**
     * Get all of the available fields to search in this search data.
     * 
     * @return all the searchable fields
     */
    public Set<String> getSearchFields() {
        return this.searchFields;
    }

    /**
     * Determine if this search data is in fact searchable, i.e. is there
     * actually anything to search.
     * 
     * @return true if there is searchable data
     */
    public boolean hasSearchableData() {
        return !data.isEmpty() && !searchFields.isEmpty();
    }

    /**
     * Determine if the given field is a searchable field.
     * 
     * @param field Field to check for searchability
     * @return true if the field exists in searchFields
     */
    public boolean isSearchableField(String field) {
        return searchFields.contains(field);
    }

    /**
     * Search the data for items that have the given search field
     * and whose value matches (or contains in the case of lists) the given
     * search value.
     * 
     * @see SearchUtil#search(List, String, String)
     * 
     * @param searchField Field/key within the maps to match upon
     * @param searchValue Value to look for in the search field
     * @return List of data items that contain a field with a name of the
     *         search field and have a value that matches the search value
     */
    public List<Map<String, Object>> search(String searchField, String searchValue) {
        return SearchUtil.search(data, searchField, searchValue);
    }
}
