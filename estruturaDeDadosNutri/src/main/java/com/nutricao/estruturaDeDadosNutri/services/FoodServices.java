package com.nutricao.estruturaDeDadosNutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.repositories.FoodRepository;

@Service
public class FoodServices {

	@Autowired
	private MealServices mealServices;

	@Autowired
	private FoodRepository repository;

	public List<Food> findAll() {
		return repository.findAll();
	}

	public Food findById(Long id) {
		Optional<Food> obj = repository.findById(id);
		return obj.get();
	}

	public Food insert(Food obj) {
		return repository.save(obj);
	}

	public Food update(Food obj) {
		if (obj.getId() == null || !repository.existsById(obj.getId())) {
			throw new RuntimeException("Food not found");
		}
		return repository.save(obj);
	}

	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Food not found");
		}
		repository.deleteById(id);
	}

}
