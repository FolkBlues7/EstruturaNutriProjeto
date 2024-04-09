package com.nutricao.estruturaDeDadosNutri.structures;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;


public class CSVReader {

    public void writeFile(DoublyLinkedList<Food> data, String path) {
        File file = new File(path);
        try {
            // Create the file, overwrite if it already exists
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists. Overwriting it.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8.name())) {
            for (Food food : data) {
                String line = String.join(",",
                        food.getName(),
                        String.valueOf(food.getProteins()),
                        String.valueOf(food.getCarbohydrates()),
                        String.valueOf(food.getLipids()),
                        String.valueOf(food.getCalories()),
                        String.valueOf(food.getWeight())
                );
                writer.println(line);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public DoublyLinkedList<Food> readFoods(String path) {
        DoublyLinkedList<Food> data = new DoublyLinkedList<>();
        File csvFile = new File(path);
        try (Scanner scanner = new Scanner(csvFile, StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                // Assuming the Food class has a constructor that takes these fields
                Food food = new Food(null, fields[0], Float.parseFloat(fields[1]), Float.parseFloat(fields[2]),
                        Float.parseFloat(fields[3]), Float.parseFloat(fields[4]), Float.parseFloat(fields[5]));
                data.add(food);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

}