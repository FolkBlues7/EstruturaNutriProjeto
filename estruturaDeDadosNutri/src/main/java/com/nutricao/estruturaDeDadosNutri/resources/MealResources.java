package com.nutricao.estruturaDeDadosNutri.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<Meal>> findAll(){
		//Food u = new Food(1L, "laranja", 20, 30, 40, 59, 5);
		//ArrayList<Food> comidas = new ArrayList<>();
		//comidas.add(u);
		//Meal meal = new Meal(1L, comidas, true, LocalDateTime.of(2024, 3, 19, 14, 30, 0));
		List<Meal> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Meal> findById(@PathVariable Long id){
		Meal obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
