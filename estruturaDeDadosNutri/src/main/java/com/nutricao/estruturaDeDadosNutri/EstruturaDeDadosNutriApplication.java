package com.nutricao.estruturaDeDadosNutri;

import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.structures.CSVReader;
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
import java.util.Scanner;

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
        ui.showFoodOptions();
    }

    private class UserInterface {

        private User user;

        public void createUser() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o nome do usuário: ");
            String name = scanner.nextLine();

            System.out.print("Digite a idade do usuário: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Digite o peso do usuário: ");
            float weight = Float.parseFloat(scanner.nextLine());

            System.out.print("Digite a altura do usuário: ");
            float height = Float.parseFloat(scanner.nextLine());

            System.out.print("Digite a porcentagem de gordura do usuário: ");
            float bodyWeight = Float.parseFloat(scanner.nextLine());

            Diet diet = new Diet();
            dietServices.insert(diet);
            User u1 = new User(null, name, age, weight, height, bodyWeight);
            u1.setDiet(diet);
            userServices.insert(u1);

        }

        public void createMeal() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a data da refeição (dd/MM/yyyy HH:mm): ");
            LocalDateTime date = LocalDateTime.parse(scanner.nextLine());
            System.out.print("Digite se a refeição já foi realizada (true/false): ");
            boolean done = Boolean.parseBoolean(scanner.nextLine());
            Meal m1 = new Meal(null, done, date);
            System.out.println("Chose the foods for the meal: ");
            DoublyLinkedList<Food> foods = showFoodOptions();
            //stoped here
            mealServices.insert(m1);
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

        public DoublyLinkedList<Food> showFoodOptions() {
            CSVReader csvReader = new CSVReader();
            DoublyLinkedList<Food> foods = csvReader.readFoods("./foods.csv");
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
            return foods;
        }

    }

}