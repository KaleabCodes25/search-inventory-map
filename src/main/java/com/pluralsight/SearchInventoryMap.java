package com.pluralsight;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

// Starter for Search Inventory Map (Workbook 3a, Section 4-1 Using a HashMap, Exercise 2).
// main is done: it asks for a product id and looks it up in the map.
// Your job is loadInventory(): put products in the map, the product id as the key.

public interface SearchInventoryMap {
    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();

    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();
        boolean isDone = false;
        Scanner scanner = new Scanner(System.in);
        while (!isDone) {
            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Product matchedProduct = inventory.get(id);

            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
                System.out.println();
            } else {
                System.out.printf("We carry %s and the price is $%.2f",
                        matchedProduct.getName(), matchedProduct.getPrice());
                System.out.println();

            }
            System.out.println("Do you want to search again?");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                isDone = false;
            } else {
                isDone = true;
            }

        }

    }

    private static void loadInventory() {
        try {
            String line;
            // Your code should be here
            BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
            // One product per line, until readLine() returns null at the end of the file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                Product product = new Product(id, name, price);
                inventory.put(id,product);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }
    }