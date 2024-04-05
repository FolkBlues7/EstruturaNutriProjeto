package com.nutricao.estruturaDeDadosNutri.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricao.estruturaDeDadosNutri.entities.Meal;
import com.nutricao.estruturaDeDadosNutri.services.MealServices;

@RestController
@RequestMapping(value = "/meals")
public class MealResources {
	@Autowired
	private MealServices service;

	@GetMapping
	public ResponseEntity<List<Meal>> findAll() {
		List<Meal> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Meal> findById(@PathVariable Long id) {
		Meal obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Meal> insert(@RequestBody Meal obj) {
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    service.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Meal> update(@PathVariable Long id, @RequestBody Meal obj){
	    obj.setId(id); // Garantir que o objeto tenha o ID correto
	    obj = service.update(obj); // Atualizar o objeto no banco de dados
	    return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/{mealId}/foods")
	public ResponseEntity<Meal> addFoodsToMeal(@PathVariable Long mealId, @RequestBody List<Long> foodIds) {
		try {
			Meal meal = service.addFoodsToMeal(mealId, foodIds);
			return ResponseEntity.ok().body(meal);
		} catch (Exception e) {
			
		}
		return null;
	}
	
	

}
