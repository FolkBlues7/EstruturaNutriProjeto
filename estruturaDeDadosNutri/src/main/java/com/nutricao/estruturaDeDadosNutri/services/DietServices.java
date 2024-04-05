package com.nutricao.estruturaDeDadosNutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.repositories.DietRepository;
import com.nutricao.estruturaDeDadosNutri.repositories.MealRepository;

@Service
public class DietServices {
	@Autowired
	private DietRepository repository;

	@Autowired
	private MealRepository mealRepository;

	@Autowired
	private MealServices mealServices; 
	// esse método encontra todos os usuários
	public List<Diet> findAll() {
		return repository.findAll();
	}

	public Diet findById(Long id) {
		Optional<Diet> obj = repository.findById(id);
		return obj.get();
	}

	public Diet insert(Diet diet) {
		return repository.save(diet);
	}

	public Diet addMealToDiet(Long dietId, Long mealId) {
		Optional<Diet> optionalDiet = repository.findById(dietId);
		if (!optionalDiet.isPresent()) {
			throw new RuntimeException("Diet not found");
		}

		Diet diet = optionalDiet.get();

		Optional<Meal> optionalMeal = mealRepository.findById(mealId);
		if (!optionalMeal.isPresent()) {
			throw new RuntimeException("Meal not found");
		}

		Meal meal = optionalMeal.get();

		// Adiciona a refeição à lista de refeições da dieta
		diet.getMeals().add(meal);
		
		mealServices.addDietToMeal(mealId, findById(dietId));

		// Atualiza a dieta no banco de dados
		return repository.save(diet);
	}

	public Diet update(Diet obj) {
		if (obj.getId() == null || !repository.existsById(obj.getId())) {
			throw new RuntimeException("Food not found");
		}
		return repository.save(obj);
	}
}
