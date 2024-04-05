package com.nutricao.estruturaDeDadosNutri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.services.DietServices;
import com.nutricao.estruturaDeDadosNutri.services.FoodServices;
import com.nutricao.estruturaDeDadosNutri.services.MealServices;

@SpringBootApplication
public class EstruturaDeDadosNutriApplication implements CommandLineRunner {

    @Autowired
    private FoodServices foodServices;
    
    @Autowired
    private MealServices mealServices;
    
    @Autowired
    private DietServices dietServices;

    public static void main(String[] args) {
        SpringApplication.run(EstruturaDeDadosNutriApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
    	//FOOD---------------------------------------------------------------------
    	//Food f1 = new Food();//vazia
    	//Food f2 = new Food(null, "batata", (float)150, (float)100, (float)40, (float)4, (float)2);
    	
    	//Food f3 = foodServices.findById(6L);
    	//f3.setName("batata-doce");
    	//foodServices.deleteById(6L);
    	
    	
    	//MEAL---------------------------------------------------------------------
    	//Meal m1 = new Meal(null, false, LocalDateTime.of(2024, 4, 5, 19, 0));
    	//mealServices.insert(m1);
    	
    	//Meal m2 = mealServices.findById(2l);
    	//Food f4 = foodServices.findById(2l);
    	//System.out.println(m2);
    	//Hibernate.initialize(m2.getFoods());
    	//m2.getFoods().add(f4);
    	//mealServices.deleteById(2l);
    	
    	
    	//Meal m4 = mealServices.findById(3L);
    	
    	//Diet diet = dietServices.findById(2L);
    	//m4.setDiet(diet);
    	//mealServices.update(m4);
    	
    	//Meal m4 = mealServices.findById(2L);
    	//m4.setDiet(diet);
    
    	//Meal m5 = new Meal(null, false, LocalDateTime.of(2024, 4, 5, 19, 0));
    	
    	//m5.getFoods().add(foodServices.findById(1L));
    	//m5.getFoods().add(foodServices.findById(2L));
    	//m5.getFoods().add(foodServices.findById(3L));
    	//mealServices.insert(m5);
    	
    	//Diet m6 = dietServices.findById(3L);
    	//dietServices.addMealToDiet(3L, 4L);
    	//System.out.println(m6.getMeals());
    	
    }
}