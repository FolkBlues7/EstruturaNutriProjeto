package com.nutricao.estruturaDeDadosNutri.services;
import java.util.List;
import java.util.Optional;

//existem coisas para substituir aqui!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.repositories.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository repository;
	//esse método encontra todos os usuários
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
