package com.nutricao.estruturaDeDadosNutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.repositories.FoodRepository;
import com.nutricao.estruturaDeDadosNutri.repositories.MealRepository;

import jakarta.transaction.Transactional;

@Service
public class MealServices {
	@Autowired
	private MealRepository repository;

	@Autowired
	private FoodRepository repositoryFood;

	// @Autowired
	// private MealServices service;
	public List<com.nutricao.estruturaDeDadosNutri.entities.Meal> findAll() {
		return repository.findAll();
	}

	public Meal findById(Long id) {
		Optional<Meal> obj = repository.findById(id);
		return obj.get();
	}
	
	public Meal insert(Meal obj) {
		return repository.save(obj);
	}

	public Meal update(Meal obj) {
		if (obj.getId() == null || !repository.existsById(obj.getId())) {
			throw new RuntimeException("Food not found");
		}
		return repository.save(obj);
	}

	@Transactional
	public Meal addFoodsToMeal(Long mealId, List<Long> foodIds) {
		Optional<Meal> optionalMeal = repository.findById(mealId);
		if (!optionalMeal.isPresent()) {
			throw new RuntimeException("Meal not found");
		}

		Meal meal = optionalMeal.get();

		// Busca os alimentos com base nos IDs fornecidos
		List<Food> foods = repositoryFood.findAllById(foodIds);

		// Adiciona os alimentos à lista de alimentos da refeição
		meal.getFoods().addAll(foods);

		// Salva a refeição atualizada
		return repository.save(meal);
	}
	
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Food not found");
		}
		repository.deleteById(id);
	}

	public Meal addDietToMeal(Long id, Diet diet) {
		Meal meal = findById(id); // Verifica se o meal com o ID existe
		// Atualiza os campos do meal com base nos campos do updatedMeal
		meal.setDiet(diet);
		// Salva e retorna o meal atualizado
		return repository.save(meal);
	}
}
