package com.nutricao.estruturaDeDadosNutri.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricao.estruturaDeDadosNutri.entities.Food;
import com.nutricao.estruturaDeDadosNutri.services.FoodServices;

//pronto
@RestController
@RequestMapping(value = "/foods")
public class FoodResources {
	
	@Autowired
	private FoodServices service;
	
	@GetMapping
	public ResponseEntity<List<Food>> findAll(){
		//Food u = new Food(1L, "melancia", 20, 30, 40, 59, 5); //teste
		List<Food> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Food> findById(@PathVariable Long id){
		Food obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
