package com.nutricao.estruturaDeDadosNutri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.services.DietServices;
import com.nutricao.estruturaDeDadosNutri.services.FoodServices;
import com.nutricao.estruturaDeDadosNutri.services.MealServices;
import com.nutricao.estruturaDeDadosNutri.services.UserServices;

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
    	//Food recuperada = foodServices.findById(7L);
    	//recuperada.setCalories(182f);
    	//salvando a food após alterações:
    	//foodServices.update(recuperada);
    	//deletando food pelo ID:
    	//foodServices.deleteById(7L);
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
    	//deta um usuário
    	//userServices.delete(4L);
    	//alterações de usuário
    	//User recuperado = userServices.findById(5L);
    	//recuperado.setAge(100);
    	//userServices.update(recuperado);
    	
    }
}