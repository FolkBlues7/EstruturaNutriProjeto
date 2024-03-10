package com.nutricao.estruturaDeDadosNutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricao.estruturaDeDadosNutri.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
