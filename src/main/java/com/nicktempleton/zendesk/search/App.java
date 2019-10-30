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
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.nicktempleton.zendesk.search.util.ResourceLoader;
import com.nicktempleton.zendesk.search.util.SearchUtil;

/**
 * Main application class
 */
public class App {
    private static final String INDENT = "    ";
    private static final String PROMPT = "> ";

    /**
     * Application execution begins here.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to Zendesk Search!");
        System.out.println("Press CTRL+C to quit at any time.");

        List<Map<String, Object>> organizations =
            ResourceLoader.loadFromJsonResource(ResourceLoader.RESOURCE_ORGANIZATIONS);
        List<Map<String, Object>> tickets =
            ResourceLoader.loadFromJsonResource(ResourceLoader.RESOURCE_TICKETS);
        List<Map<String, Object>> users =
            ResourceLoader.loadFromJsonResource(ResourceLoader.RESOURCE_USERS);

        Set<String> organizationFields = SearchUtil.searchableFields(organizations);
        Set<String> ticketFields = SearchUtil.searchableFields(tickets);
        Set<String> userFields = SearchUtil.searchableFields(users);

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                System.out.println();
                System.out.println("Please enter an option:");
                System.out.println(INDENT + "1) Search Organizations");
                System.out.println(INDENT + "2) Search Tickets");
                System.out.println(INDENT + "3) Search Users");
                System.out.println(INDENT + "4) Quit");
                System.out.print(PROMPT);
                input = scanner.nextLine().trim();
                if ("1".equals(input)) {
                    String searchField = promptForSearchableField(organizationFields, scanner);
                    String searchValue = promptForSearchValue(scanner);
                    List<Map<String, Object>> results =
                        SearchUtil.search(organizations, searchField, searchValue);
                    printResults(results);
                } else if ("2".equals(input)) {
                    String searchField = promptForSearchableField(ticketFields, scanner);
                    String searchValue = promptForSearchValue(scanner);
                    List<Map<String, Object>> results =
                        SearchUtil.search(tickets, searchField, searchValue);
                    printResults(results);
                } else if ("3".equals(input)) {
                    String searchField = promptForSearchableField(userFields, scanner);
                    String searchValue = promptForSearchValue(scanner);
                    List<Map<String, Object>> results =
                        SearchUtil.search(users, searchField, searchValue);
                    printResults(results);
                }
            } while (!"4".equals(input));
        }
    }

    private static String promptForSearchableField(Set<String> searchableFields, Scanner scanner) {
        String searchableField;
        do {
            System.out.println();
            System.out.println("Searchable fields:");
            for (String field : searchableFields) {
                System.out.println(INDENT + field);
            }
            System.out.println();
            System.out.println("Enter desired field to search:");
            System.out.print(PROMPT);
            searchableField = scanner.nextLine().trim();
        } while (!searchableFields.contains(searchableField));

        return searchableField;
    }

    private static String promptForSearchValue(Scanner scanner) {
        System.out.println();
        System.out.println("Enter search value:");
        System.out.print(PROMPT);
        return scanner.nextLine().trim();
    }

    private static void printResults(List<Map<String, Object>> results) {
        System.out.println();
        System.out.println("Results:");

        for (Map<String, Object> listItem : results) {
            System.out.println();
            for (Map.Entry<String, Object> entry : listItem.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Double) {
                    // Gson gives us doubles, but let's display integers
                    System.out.printf("%-16s %.0f\n", entry.getKey(), value);
                } else {
                    System.out.printf("%-16s %s\n", entry.getKey(), value);
                }
            }
        }

        System.out.println();
        System.out.println("Result count: " + results.size());
    }
}
