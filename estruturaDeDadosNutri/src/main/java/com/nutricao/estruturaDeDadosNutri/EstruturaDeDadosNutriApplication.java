package com.nutricao.estruturaDeDadosNutri;

import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.structures.CSVReader;
import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.LinkedQueue;
import com.nutricao.estruturaDeDadosNutri.structures.SearchMethods.BinarySearch;
import com.nutricao.estruturaDeDadosNutri.structures.SortingMethods.BubbleSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nutricao.estruturaDeDadosNutri.services.DietServices;
import com.nutricao.estruturaDeDadosNutri.services.FoodServices;
import com.nutricao.estruturaDeDadosNutri.services.MealServices;
import com.nutricao.estruturaDeDadosNutri.services.UserServices;
import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class EstruturaDeDadosNutriApplication implements CommandLineRunner {

    @Autowired
    private FoodServices foodServices;
    
    @Autowired
    private MealServices mealServices;
    
    @Autowired
    private DietServices dietServices;
    
    @Autowired
    private UserServices userServices;

    public static void main(String[] args) {
        SpringApplication.run(EstruturaDeDadosNutriApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        UserInterface ui = new UserInterface();
        System.out.println("Available users: ");
        List<User> users = userServices.findAll();
        long userId = 1L;
        if (!users.isEmpty()) {
            int c = 1;
            for (User user : users) {
                System.out.println(c + ". " + user.getName());
                c++;
            }
            System.out.print(users.size() + 1 + ". Create a new User\n");
            System.out.print("--> ");
            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.nextLine());
            if (option > 0 && option <= users.size()) {
                userId = users.get(option-1).getId();
            } else {
                userId = ui.createUser();
            }
        } else {
            System.out.println("No users found.");
            System.out.println("Creating a new User...");
            userId = ui.createUser();
        }

        String name = userServices.findById(userId).getName();
        System.out.println("\nWelcome, " + name + "!");

        ui.syncronizeCSVDatabase();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Choose an option: ");
            System.out.println("1. Create a new Meal for my Diet");
            System.out.println("2. See my current Meals");
            System.out.println("3. Add foods to database");
            System.out.println("4. See all foods in database");
            System.out.println("5. Delete a food from database");
            System.out.println("6. Delete a meal from database");
            System.out.println("7. Update user information");
            System.out.println("8. Count total calories in Diet");
            System.out.println("0. Exit");
            System.out.print("--> ");
            int option = Integer.parseInt(scanner.nextLine());
            System.out.println();
            if (option == 1) {
                ui.createMeal(userId);
            } else if (option == 2) {
                Diet diet = userServices.findById(userId).getDiet();
                if (diet.getMeals().isEmpty()) {
                    System.out.println("No meals in your diet yet.");
                } else {
                    while (true) {
                        System.out.println("Your current Diet is composed by the following Meals:\n");
                        List<Meal> meals = diet.getMeals();
                        BubbleSort<Meal> bs = new BubbleSort<>();
                        meals = bs.sort(meals);
                        int c = 1;
                        for (Meal meal : meals) {
                            System.out.println(c + ". " + "Meal at " + meal.getMealTime());
                            System.out.print("Composed by: ");
                            List<String> foodNames = meal.getFoods().stream()
                                    .map(Food::getName)
                                    .collect(Collectors.toList());
                            System.out.println(String.join(", ", foodNames));
                            c++;
                        }
                        System.out.print("--> ");
                        int mealOption = Integer.parseInt(scanner.nextLine());
                        if (mealOption > 0 && mealOption <= meals.size()) {
                            Meal meal = meals.get(mealOption - 1);
                            System.out.println("\nMeal at " + meal.getMealTime());
                            System.out.println("Composed by: ");
                            List<Food> foodNames = meal.getFoods();
                            for (Food food : foodNames) {
                                System.out.println(food.getName() + ", " + food.getWeight() + ", " + food.getCalories() + " calorias, " + food.getCarbohydrates() + "g de carboidratos, " + food.getProteins() + "g de proteinas, " + food.getLipids() + "g de lipidios");
                            }
                            System.out.println();
                            break;
                        } else {
                            System.out.println("Invalid option.");
                        }
                    }
                }
            } else if (option == 3) {
                System.out.println("Adding foods to database...");
                while (true) {
                    System.out.print("Name: ");
                    String foodName = scanner.nextLine();
                    System.out.print("Calories: ");
                    float calories = Float.parseFloat(scanner.nextLine());
                    System.out.print("Weight: ");
                    float weight = Float.parseFloat(scanner.nextLine());
                    System.out.print("Carbohydrates: ");
                    float carbohydrates = Float.parseFloat(scanner.nextLine());
                    System.out.print("Proteins: ");
                    float proteins = Float.parseFloat(scanner.nextLine());
                    System.out.print("Lipids: ");
                    float lipids = Float.parseFloat(scanner.nextLine());
                    Food food = new Food(null, foodName, calories, weight, carbohydrates, proteins, lipids);
                    foodServices.insert(food);
                    System.out.print("Add another food? (true/false): ");
                    String input = scanner.nextLine().toLowerCase();
                    if (input.equals("false")) {
                        break;
                    }
                }
                ui.syncronizeCSVDatabase();
            } else if (option == 4) {
                List<Food> foods = foodServices.findAll();
                ui.showFoodOptions(foods);
            } else if (option == 5) {
                System.out.println("Deleting a food from database...");
                while (true) {
                    System.out.println("Choose a food to delete: ");
                    List<Food> foods = foodServices.findAll();
                    ui.showFoodOptions(foods);
                    System.out.println("0. Finish deleting foods");
                    System.out.print("--> ");
                    int foodOption = Integer.parseInt(scanner.nextLine());
                    if (foodOption > 0 && foodOption <= foods.size()) {
                        Food food = foods.get(foodOption - 1);
                        BinarySearch<Food> bs = new BinarySearch<>();
                        DoublyLinkedList<Food> allFoods = new DoublyLinkedList<>();
                        for (Meal meal : mealServices.findAll()) {
                            allFoods.addAll(meal.getFoods());
                        }
                        if (bs.search(allFoods, food) == -1) {
                            ui.deleteFood(food, food.getId());
                            ui.syncronizeCSVDatabase();
                        } else {
                            System.out.println("\nThis food is in use and cannot be deleted.");
                        }
                    } else {
                        System.out.println("Exiting...");
                        break;
                    }
                    System.out.println();
                }

            } else if (option == 6) {
                System.out.println("Deleting a meal from database...");
                List<Meal> meals = dietServices.findById(userId).getMeals();
                BubbleSort<Meal> bs = new BubbleSort<>();
                meals = bs.sort(meals);
                LinkedQueue<Meal> queue = new LinkedQueue<>();
                queue.addAll(meals);
                if (queue.isEmpty()) {
                    System.out.println("No meals in your diet yet.");
                    continue;
                } else {
                    System.out.println("Here are your Meals: ");
                    int c = 1;
                    for (Meal meal : meals) {
                        System.out.println(c + ". " + meal.getMealTime());
                        c++;
                    }
                }
                System.out.print("Remove the first meal (Y/N)? --> ");
                String input = scanner.nextLine().toLowerCase();
                if (input.equals("y")) {
                    Meal meal = queue.remove();
                    ui.deleteMeal(meal);
                } else {
                    System.out.println("Canceled operation.");
                }
            } else if (option == 7) {
                User user = userServices.findById(userId);
                System.out.println("Current user information: ");
                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge() + " years");
                System.out.println("Weight: " + user.getWeight() + " kg");
                System.out.println("Height: " + user.getHeight() + " cm");
                System.out.println("Body fat: " + user.getBodyWeight() + "%");
                System.out.println();
                System.out.println("Updating user information...");
                while (true) {
                    System.out.println("Which information would you like to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Age");
                    System.out.println("3. Weight");
                    System.out.println("4. Height");
                    System.out.println("5. Body fat");
                    System.out.println("0. Finish updating");
                    System.out.print("--> ");
                    int updateOption = Integer.parseInt(scanner.nextLine());
                    if (updateOption == 1) {
                        System.out.print("New name: ");
                        String name2 = scanner.nextLine();
                        user.setName(name2);
                    } else if (updateOption == 2) {
                        System.out.print("New age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        user.setAge(age);
                    } else if (updateOption == 3) {
                        System.out.print("New weight: ");
                        float weight = Float.parseFloat(scanner.nextLine());
                        user.setWeight(weight);
                    } else if (updateOption == 4) {
                        System.out.print("New height: ");
                        float height = Float.parseFloat(scanner.nextLine());
                        user.setHeight(height);
                    } else if (updateOption == 5) {
                        System.out.print("New body fat: ");
                        float bodyWeight = Float.parseFloat(scanner.nextLine());
                        user.setBodyWeight(bodyWeight);
                    } else {
                        System.out.println("Exiting...");
                        break;
                    }
                }
                userServices.update(user);
                System.out.println("User information updated.");
            } else if (option == 8) {
                System.out.println("Counting total calories in Diet...");
                Diet diet = userServices.findById(userId).getDiet();
                if (diet.getMeals().isEmpty()) {
                    System.out.println("No Meals in your Diet yet.");
                } else {
                    float totalCalories = ui.calculateTotalCaloriesInDiet(diet.getMeals(), 0);
                    System.out.println("Total calories in your Diet: " + totalCalories);
                }

            } else {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    private class UserInterface {

        public Long createUser() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("What is the user's name: ");
            String name = scanner.nextLine();

            System.out.print("What is the user's age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("What is the user's weight (kg): ");
            float weight = Float.parseFloat(scanner.nextLine());

            System.out.print("What is the user's height (cm): ");
            float height = Float.parseFloat(scanner.nextLine());

            System.out.print("What is the user's body fat (%): ");
            float bodyWeight = Float.parseFloat(scanner.nextLine());

            Diet diet = new Diet();
            dietServices.insert(diet);
            User u1 = new User(null, name, age, weight, height, bodyWeight);
            u1.setDiet(diet);
            User savedUser = userServices.insert(u1);

            return savedUser.getId();
        }

        public void createMeal(long userId) {
            Scanner scanner = new Scanner(System.in);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = null;

            while (date == null) {
                System.out.print("Set the date for your Meal (dd/MM/yyyy HH:mm): ");
                String input = scanner.nextLine();
                try {
                    date = LocalDateTime.parse(input, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid format. Please try again.");
                }
            }

            boolean done;
            System.out.print("Is your Meal already done? (true/false): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true")) {
                done = true;
            } else if (input.equals("false")) {
                done = false;
            } else {
                done = false;
                System.out.println("Invalid input. Meal will be considered not done.");
            }

            Meal m1 = new Meal(null, done, date);
            m1 = mealServices.insert(m1); // Insert the meal and get the saved meal with its ID
            dietServices.addMealToDiet(userId, m1.getId()); // Diet and User are hardcoded to same ID
            while (true) {
                System.out.println();
                System.out.println("Choose the following Foods for the new meal: ");
                List<Food> foods = foodServices.findAll();
                showFoodOptions(foods);
                System.out.println(foods.size() + 1 + ". Finish adding Foods to Meal");
                System.out.print("--> ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option > foods.size() || option < 1) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    Food food = foods.get(option - 1);
                    List<Long> foodsId = new DoublyLinkedList<>();
                    foodsId.add(food.getId());
                    mealServices.addFoodsToMeal(m1.getId(), foodsId);
                }
            }
        }

        public void syncronizeCSVDatabase() {
            CSVReader csvReader = new CSVReader();
            DoublyLinkedList<Food> foods = csvReader.readFoods("./foods.csv");

            // If foods is null, initialize it to an empty list
            if (foods == null) {
                foods = new DoublyLinkedList<>();
            }

            Set<Food> foodSet = new HashSet<>(foods);

            List<Food> databaseFoods = foodServices.findAll();

            Set<Food> databaseFoodSet = new HashSet<>(databaseFoods);

            for (Food food : foodSet) {
                // If Food is not in the database, add it to the database
                if (!databaseFoodSet.contains(food)) {
                    foodServices.insert(food);
                }
            }

            // Get all foods from the database
            List<Food> allDatabaseFoods = foodServices.findAll();

            // Convert the allDatabaseFoods List back to a DoublyLinkedList
            DoublyLinkedList<Food> list = new DoublyLinkedList<>();
            list.addAll(allDatabaseFoods);

            // Write all foods from the database back to the CSV file
            csvReader.writeFile(list, "./foods.csv");
        }

        public void deleteFood(Food food, Long id) {
            Food foodFound = foodServices.findById(id);
            foodServices.deleteById(foodFound.getId());
            CSVReader csvReader = new CSVReader();
            DoublyLinkedList<Food> foods = csvReader.readFoods("./foods.csv");
            Food[] foodsArray = foods.toArray(new Food[0]);
            for (int i = 0; i < foodsArray.length; i++) {
                foodsArray[i] = foods.get(i);
            }
            BinarySearch<Food> bs = new BinarySearch<>();
            int index = bs.search(foods, food);
            if (index == -1) {
                System.out.println("Food not found.");
                return;
            } else {
                foods.clear();
                for (int i = 0; i < foodsArray.length; i++) {
                    if (i != index) {
                        foods.add(foodsArray[i]);
                    }
                }
                csvReader.writeFile(foods, "./foods.csv");
                System.out.println("Food deleted.");
            }
        }

        public void deleteMeal(Meal meal) {
            mealServices.deleteById(meal.getId());
            System.out.println("Meal deleted.");
        }

        public void showFoodOptions(List<Food> foods) {
            int c = 1;
            for (Food food : foods) {
                System.out.print(c + ". " + food.getName());
                System.out.print(" - ");
                System.out.print(food.getWeight() + "g de peso, ");
                System.out.print(food.getCalories() + " calorias, ");
                System.out.print(food.getCarbohydrates() + "g de carboidratos, ");
                System.out.print(food.getProteins() + "g de proteinas, ");
                System.out.print(food.getLipids() + "g de lipidios");
                System.out.println();
                c++;
            }
        }

        public float calculateTotalCaloriesInMeal(List<Food> foods, int index) {
            if (index == foods.size()) {
                return 0;
            } else {
                return foods.get(index).getCalories() + calculateTotalCaloriesInMeal(foods, index + 1);
            }
        }

        public float calculateTotalCaloriesInDiet(List<Meal> meals, int index) {
            if (index == meals.size()) {
                return 0;
            } else {
                float mealCalories = calculateTotalCaloriesInMeal(meals.get(index).getFoods(), 0);
                return mealCalories + calculateTotalCaloriesInDiet(meals, index + 1);
            }
        }

    }

}