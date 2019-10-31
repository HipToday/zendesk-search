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

import com.nicktempleton.zendesk.search.model.SearchData;

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

        SearchData organizations = new SearchData("organizations.json");
        SearchData tickets = new SearchData("tickets.json");
        SearchData users = new SearchData("users.json");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean quit = false;
            do {
                System.out.println();
                System.out.println("Please enter an option:");
                System.out.println(INDENT + "1) Search Organizations");
                System.out.println(INDENT + "2) Search Tickets");
                System.out.println(INDENT + "3) Search Users");
                System.out.println(INDENT + "4) Quit");
                System.out.print(PROMPT);
                String menuOption = scanner.nextLine().trim();

                switch (menuOption) {
                    case "1":
                        search(organizations, scanner);
                        break;
                    case "2":
                        search(tickets, scanner);
                        break;
                    case "3":
                        search(users, scanner);
                        break;
                    case "4":
                        quit = true;
                        break;
                    default:
                        System.out.println();
                        System.out.println("ERROR: Invalid option, try again");
                    }
            } while (!quit);
        }
    }

    private static void search(SearchData data, Scanner scanner) {
        if (!data.hasSearchableData()) {
            System.out.println();
            System.out.println("Nothing to search!");
            return;
        }

        String searchField = promptForSearchableField(data, scanner);
        String searchValue = promptForSearchValue(scanner);
        List<Map<String, Object>> results = data.search(searchField, searchValue);

        printResults(results);
    }

    private static String promptForSearchableField(SearchData data, Scanner scanner) {
        String searchableField;

        boolean validField = false;
        do {
            System.out.println();
            System.out.println("Searchable fields:");
            for (String field : data.getSearchFields()) {
                System.out.println(INDENT + field);
            }
            System.out.println();
            System.out.println("Enter desired field to search:");
            System.out.print(PROMPT);
            searchableField = scanner.nextLine().trim();

            validField = data.isSearchableField(searchableField);
            if (!validField) {
                System.out.println();
                System.out.println("ERROR: Invalid field, try again");
            }
        } while (!validField);

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
