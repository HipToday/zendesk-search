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
package com.nicktempleton.zendesk.search;

import java.util.List;

import com.nicktempleton.zendesk.search.model.Organization;
import com.nicktempleton.zendesk.search.util.ResourceLoader;

/**
 * Main application class
 */
public class App {
    private static List<Organization> organizations;

    /**
     * Application execution begins here.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Zendesk Search!");

        organizations = ResourceLoader.loadFromJSONResource("organizations.json");

        System.out.println("Organization count: " + organizations.size());
        for (Organization org : organizations) {
            System.out.println(org);
        }
    }
}
