package com.nutricao.estruturaDeDadosNutri.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@Autowired
	private UserServices service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		//User u = new User(1L, "Maria", 20, (float)49.6 , (float)1.65, (float)50, 12);
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
