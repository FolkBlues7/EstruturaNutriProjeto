package com.nutricao.estruturaDeDadosNutri.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.services.DietServices;

@RestController
@RequestMapping(value = "/diets")
public class DietResources {
	
	@Autowired
	private DietServices service;
	
	@GetMapping
	public ResponseEntity<List<Diet>> findAll(){
		//Food u = new Food(1L, "laranja", 20, 30, 40, 59, 5);
		//ArrayList<Food> comidas = new ArrayList<>();
		//comidas.add(u);
		//Meal meal = new Meal(1L, comidas, true, LocalDateTime.of(2024, 3, 19, 14, 30, 0));
		//ArrayList<Meal> refeicoes = new ArrayList<>();
		//refeicoes.add(meal);
		//Diet diet = new Diet(refeicoes);
		
		List<Diet> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Diet> findById(@PathVariable Long id){
		Diet obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Diet> insert(@RequestBody Diet obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
