package com.nutricao.estruturaDeDadosNutri.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.repositories.FoodRepository;
import com.nutricao.estruturaDeDadosNutri.repositories.UserRepository;
//essa classe serve para testar e fazer a inserção no BD do h2
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FoodRepository foodRepository;
    
    //@Autowired
    //private MealRepository mealRepository;
    
    //@Autowired
    //private DietRepository dietRepository;

	//TUDO DENTRO DESSE MÉTODO SERÁ RODADO JUNTO DO INÍCIO
	@Override
	public void run(String... args) throws Exception {
		//User u1 = new User(null, "Maria", 20, (float)49.6 , (float)1.65, (float)50, 12, null);
		//User u2 = new User(null, "João", 20, (float)35.6 , (float)1.65, (float)50, 12, null);
		
		//Food f1 = new Food(null, "Macarrão", (float)20, (float)10, (float)4, (float)3, (float)3);
		//Food f2 = new Food(null, "Fe", 85, 100, 26, 6, 2);
		//Food f3 = new Food(null, "Ar", 95, 100, 29, 3, 1);
		//Food f4 = new Food(null, "Ca", 179, 100, 37, 0, 3);
	
		//ArrayList<Food> comidas = new ArrayList<>();
		//comidas.add(f1);// comidas.add(f2); comidas.add(f3); comidas.add(f4);
		//Meal m1 = new Meal(null, comidas, true, LocalDateTime.of(2024, 3, 19, 14, 30, 0));
		//Meal m2 = new Meal(null, comidas, true, LocalDateTime.of(2024, 3, 19, 14, 30, 0));
		//Meal m3 = new Meal(null, comidas, true, LocalDateTime.of(2024, 3, 19, 14, 30, 0));
		//Meal m4 = new Meal(null, comidas, true, LocalDateTime.of(2024, 3, 19, 14, 30, 0));
		
		//ArrayList<Meal> refeicoes = new ArrayList<>();
		//refeicoes.add(m1); //refeicoes.add(m2); refeicoes.add(m3); refeicoes.add(m4);
		//Diet dieta = new Diet(refeicoes);
		//foodRepository.save(f1);
		//mealRepository.saveAll(Arrays.asList(m1));
		//dietRepository.save(dieta);
		//userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
}
