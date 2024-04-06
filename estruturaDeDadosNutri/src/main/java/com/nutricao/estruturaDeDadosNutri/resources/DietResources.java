package com.nutricao.estruturaDeDadosNutri.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.services.DietServices;
import com.nutricao.estruturaDeDadosNutri.services.MealServices;

@RestController
@RequestMapping(value = "/diets")
public class DietResources {

	@Autowired
	private MealServices mealServices;
	
	@Autowired
	private DietServices service;

	@GetMapping
	public ResponseEntity<List<Diet>> findAll() {
		List<Diet> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Diet> findById(@PathVariable Long id) {
		Diet obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Diet> insert(@RequestBody Diet obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PostMapping("/mealAddToDiet/{dietId}")
    public ResponseEntity<Diet> addMealToDiet(@PathVariable Long dietId, @RequestBody Long mealId) {
        // Adiciona a refeição à dieta
        Diet updatedDiet = service.addMealToDiet(dietId, mealId);
        mealServices.addDietToMeal(mealId, updatedDiet);
        // Retorna a dieta atualizada
        return ResponseEntity.ok().body(updatedDiet);
    }
	

	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Diet> update(@PathVariable Long id, @RequestBody Diet obj){
	    obj.setId(id); // Garantir que o objeto tenha o ID correto
	    obj = service.update(obj); // Atualizar o objeto no banco de dados
	    return ResponseEntity.ok().body(obj);
	}
}
