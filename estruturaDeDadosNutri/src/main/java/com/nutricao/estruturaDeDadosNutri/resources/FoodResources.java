package com.nutricao.estruturaDeDadosNutri.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		List<Food> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Food> findById(@PathVariable Long id){
		Food obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Food> insert(@RequestBody Food obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Food> update(@PathVariable Long id, @RequestBody Food obj){
	    obj.setId(id); // Garantir que o objeto tenha o ID correto
	    obj = service.update(obj); // Atualizar o objeto no banco de dados
	    return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    service.deleteById(id);
	    return ResponseEntity.noContent().build();
	}

	
}
