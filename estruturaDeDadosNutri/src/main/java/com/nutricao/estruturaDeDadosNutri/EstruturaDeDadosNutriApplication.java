package com.nutricao.estruturaDeDadosNutri;

import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.structures.CSVReader;
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
import java.util.List;
import java.util.Scanner;
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

    	//FOOD:--------------------------------------------------------------------
    	//criando uma nova food:
    	//Food f1 = new Food(null, "carne-de-sol", 159, 100, 0, 32, 2);
    	//inserindo a nova food no banco de dados:
    	//foodServices.insert(f1);
    	//recuperando a food inserida pelo ID:
    	//Food recuperada = foodServices.findById(2L);
    	//recuperada.setCalories(182f);
    	//salvando a food após alterações:
    	//foodServices.update(recuperada);
    	//deletando food pelo ID:
    	//foodServices.deleteById(3L);
    	//-------------------------------------------------------------------------

    	//MEAL:--------------------------------------------------------------------
    	//criando uma nova meal LocalDateTime.of(2024, 4, 5, 19, 0) é utilizando para definidir a data da refeição
    	//Meal m1 = new Meal(null, true, LocalDateTime.of(2024, 4, 5, 19, 0));
    	//inserindo uma nova meal no banco de dados:
    	//mealServices.insert(m1);
    	//recuperando algumas foods para adicionar à meal, esse método adiciona todos os ID's contidos no arraylist LONG
    	//Meal recuperada = mealServices.findById(5L);
    	//Food f1 = foodServices.findById(2L);
    	//List<Long> foodsId = new ArrayList<>();
    	//foodsId.add(f1.getId());
    	//Agora você tem uma lista de IDs de alimentos
    	//Chame o método addFoodsToMeal passando a lista de IDs de alimentos
    	//mealServices.addFoodsToMeal(6L, foodsId);
    	//deletando meal
    	//mealServices.deleteById(5L); //também apagar os relacionamento de meal_food
    	//repare que update não é necessário
    	
    	//Diet:--------------------------------------------------------------------
    	//criando uma dieta, que deve iniciar vazia, e depois adicionamos a lista de meals
    	//Diet d1 = new Diet();
    	//inserindo a dieta criada ao banco de dados:
    	//dietServices.insert(d1);
    	//cada meal deve pertencer a apenas uma dieta
    	//recuperando a dieta criada:
    	//Diet recuperada = dietServices.findById(4L);
    	//adicionando uma meal à Diet, recebe o ID da dieta e o ID da refeição:
    	//dietServices.addMealToDiet(4L,6L);
    	//deletar uma dieta não é POSSÍVEL
    	
    	//User:---------------------------------------------------------------------
    	//criando um usuário: o usuário deve ser criado junto de uma Diet.
    	//User u1 = new User(null, "Waluigi", 40, (float)45, 90, 32, 32);
    	//Diet diet = new Diet();
    	//dietServices.insert(diet);
    	//configure a referência da entidade User para a entidade Diet recém-salva
    	//Podemos concluir que uma dieta será criada apenas junta de um usuário
    	//u1.setDiet(diet);
    	//salve a entidade User
    	//userServices.insert(u1);
    	//deleta um usuário
    	//userServices.delete(4L);
    	//alterações de usuário
    	//User recuperado = userServices.findById(5L);
    	//recuperado.setAge(100);
    	//userServices.update(recuperado);

        UserInterface ui = new UserInterface();
        //String name = ui.createUser();
        //ui.addFoodOptionsToDatabase();
        String name = userServices.findById(1L).getName();
        System.out.println("\nWelcome, " + name + "!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Choose an option: ");
            System.out.println("1. Create a new Meal for my Diet");
            System.out.println("2. See my current Diet's Meals");
            System.out.println("3. Exit");
            System.out.print("--> ");
            int option = Integer.parseInt(scanner.nextLine());
            System.out.println();
            if (option == 1) {
                ui.createMeal();
            } else if (option == 2) {
                Diet diet = userServices.findById(1L).getDiet();
                if (diet.getMeals().isEmpty()) {
                    System.out.println("No meals in your diet yet.");
                } else {
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
                    
                }
            } else if (option == 3) {
                break;
            }
        }
    }

    private class UserInterface {

        private static long userCounter = 1L;

        public String createUser() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o nome do usuário: ");
            String name = scanner.nextLine();

            System.out.print("Digite a idade do usuário: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Digite o peso do usuário: ");
            float weight = Float.parseFloat(scanner.nextLine());

            System.out.print("Digite a altura do usuário: ");
            float height = Float.parseFloat(scanner.nextLine());

            System.out.print("Digite a porcentagem de gordura corporal do usuário: ");
            float bodyWeight = Float.parseFloat(scanner.nextLine());

            Diet diet = new Diet();
            dietServices.insert(diet);
            User u1 = new User(userCounter, name, age, weight, height, bodyWeight);
            u1.setDiet(diet);
            userServices.insert(u1);

            return name;
        }

        public void createMeal() {
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
            dietServices.addMealToDiet(userCounter, m1.getId()); // Diet and User are hardcoded to same ID
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

        public void writeFoodOptionsExamples() {
            CSVReader csvReader = new CSVReader();
            DoublyLinkedList<Food> foods = new DoublyLinkedList<>();
            foods.add(new Food(null, "carne-de-sol", 159, 100, 0, 32, 2));
            foods.add(new Food(null, "arroz", 130, 100, 28, 2, 0));
            foods.add(new Food(null, "feijão", 100, 100, 20, 5, 1));
            foods.add(new Food(null, "macarrão", 130, 100, 28, 2, 0));
            foods.add(new Food(null, "batata", 77, 100, 17, 2, 0));
            foods.add(new Food(null, "ovo", 70, 100, 1, 6, 5));
            csvReader.writeFile(foods, "./foods.csv");
        }

        public void addFoodOptionsToDatabase() {
            CSVReader csvReader = new CSVReader();
            DoublyLinkedList<Food> foods = csvReader.readFoods("./foods.csv");
            for (Food food : foods) {
                foodServices.insert(food);
            }
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

    }

}