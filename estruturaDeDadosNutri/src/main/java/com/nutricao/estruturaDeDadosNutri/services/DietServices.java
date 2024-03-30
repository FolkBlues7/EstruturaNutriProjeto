package com.nutricao.estruturaDeDadosNutri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;
import com.nutricao.estruturaDeDadosNutri.repositories.DietRepository;

@Service
public class DietServices {
	@Autowired
	private DietRepository repository;

	// esse método encontra todos os usuários
	public List<Diet> findAll() {
		return repository.findAll();
	}

	public Diet findById(Long id) {
		Optional<Diet> obj = repository.findById(id);
		return obj.get();
	}
}
