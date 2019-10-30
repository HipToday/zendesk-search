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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.nicktempleton.zendesk.search.util.ResourceLoader;

/**
 * Main application class
 */
public class App {
    /**
     * Application execution begins here.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Zendesk Search!");

        List<Map<String, Object>> organizations =
            ResourceLoader.loadFromJsonResource(ResourceLoader.RESOURCE_ORGANIZATIONS);
        List<Map<String, Object>> tickets =
            ResourceLoader.loadFromJsonResource(ResourceLoader.RESOURCE_TICKETS);
        List<Map<String, Object>> users =
            ResourceLoader.loadFromJsonResource(ResourceLoader.RESOURCE_USERS);

        Set<String> organizationFields = searchableFields(organizations);
        Set<String> ticketFields = searchableFields(tickets);
        Set<String> userFields = searchableFields(users);
    
        System.out.println();
        System.out.println("Organization count: " + organizations.size());
        System.out.println("Ticket count: " + tickets.size());
        System.out.println("User count: " + users.size());

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                System.out.println();
                System.out.println("Please select an option:");
                System.out.println("1 - Search Organizations");
                System.out.println("2 - Search Tickets");
                System.out.println("3 - Search Users");
                System.out.println("4 - Quit");
                System.out.print("> ");
                input = scanner.nextLine();
                if ("1".equals(input)) {
                    String searchField = promptForSearchableField(organizationFields, scanner);
                    String searchValue = promptForSearchValue(scanner);
                    search(organizations, searchField, searchValue);
                } else if ("2".equals(input)) {
                    String searchField = promptForSearchableField(ticketFields, scanner);
                    String searchValue = promptForSearchValue(scanner);
                    search(tickets, searchField, searchValue);
                } else if ("3".equals(input)) {
                    String searchField = promptForSearchableField(userFields, scanner);
                    String searchValue = promptForSearchValue(scanner);
                    search(users, searchField, searchValue);
                }
            } while (!"4".equals(input));
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> search(List<Map<String, Object>> data, String searchField, String searchValue) {
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

    private static Set<String> searchableFields(List<Map<String, Object>> data) {
        Set<String> searchableFields = new HashSet<>();
        for (Map<String, Object> org : data) {
            searchableFields.addAll(org.keySet());
        }
        return searchableFields;
    }

    private static String promptForSearchableField(Set<String> searchableFields, Scanner scanner) {
        String searchableField;
        do {
            System.out.println();
            System.out.println("Searchable fields:");
            searchableFields.forEach(System.out::println);
            System.out.println("Enter desired field to search:");
            System.out.print("> ");
            searchableField = scanner.nextLine();
        } while (!searchableFields.contains(searchableField));

        return searchableField;
    }

    private static String promptForSearchValue(Scanner scanner) {
        System.out.println();
        System.out.println("Enter search value:");
        System.out.print("> ");
        return scanner.nextLine();
    }
}
