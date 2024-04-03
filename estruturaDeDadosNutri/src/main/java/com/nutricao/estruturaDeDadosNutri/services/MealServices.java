package com.nutricao.estruturaDeDadosNutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.repositories.MealRepository;

@Service
public class MealServices {
	@Autowired
	private MealRepository repository;

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
}
