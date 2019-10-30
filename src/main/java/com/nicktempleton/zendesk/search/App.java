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

        System.out.println("Organization count: " + organizations.size());
        System.out.println("Ticket count: " + tickets.size());
        System.out.println("User count: " + users.size());

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                System.out.println("Please select an option:");
                System.out.println("1 - Search Organizations");
                System.out.println("2 - Search Tickets");
                System.out.println("3 - Search Users");
                System.out.println("4 - Quit");
                System.out.print("> ");
                input = scanner.next();
                if ("1".equals(input)) {
                    search(organizations, scanner);
                } else if ("2".equals(input)) {
                    search(tickets, scanner);
                } else if ("3".equals(input)) {
                    search(users, scanner);
                }
            } while (!"4".equals(input));
        }
    }

    @SuppressWarnings("unchecked")
    private static void search(List<Map<String, Object>> data, Scanner scanner) {
        String searchField = promptForSearchableField(data, scanner);
        if (data.get(0).keySet().contains(searchField)) {
            String searchValue = promptForSearchValue(scanner);
            List<Map<String, Object>> results = new ArrayList<>();
            for (Map<String, Object> listItem : data) {
                Object fieldValue = listItem.get(searchField);
                System.out.println(null != fieldValue ? fieldValue.getClass() : "null");
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
//             List<Map<String, Object>> result = data.stream()
//                 .filter(org -> null != org.get(searchField))
//                 .filter(Predicates.isDouble(searchField)
//                     .and(org -> org.get(searchField).equals(Double.valueOf(searchValue)))
//                     .or(Predicates.isDouble(searchField).negate()
//                         .and(org -> org.get(searchField).toString().contains(searchValue))))
// //                .filter(org -> org.get(searchField).toString().contains(searchValue))
//                 .collect(Collectors.toList());
//             result.forEach(System.out::println);
        }
    }

    private static String promptForSearchableField(List<Map<String, Object>> data, Scanner scanner) {
        Set<String> searchableFields = new HashSet<>();
        for (Map<String, Object> org : data) {
            searchableFields.addAll(org.keySet());
        }

        System.out.println("Searchable fields:");
        searchableFields.forEach(System.out::println);
        System.out.println("Enter desired field to search:");
        System.out.print("> ");
        return scanner.next();
    }

    private static String promptForSearchValue(Scanner scanner) {
        System.out.println("Enter search value:");
        System.out.print("> ");
        return scanner.next();
    }
}
