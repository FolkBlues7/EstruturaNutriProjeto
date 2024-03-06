package com.nutricao.estruturaDeDadosNutri.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricao.estruturaDeDadosNutri.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", 20, (float)49.6 , (float)1.65, (float)50, 12);
		return ResponseEntity.ok().body(u);
	}
}
