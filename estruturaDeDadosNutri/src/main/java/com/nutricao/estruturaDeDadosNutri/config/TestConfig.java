package com.nutricao.estruturaDeDadosNutri.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nutricao.estruturaDeDadosNutri.entities.User;
import com.nutricao.estruturaDeDadosNutri.repositories.UserRepository;
//essa classe serve para testar e fazer a inserção no BD do h2
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria", 20, (float)49.6 , (float)1.65, (float)50, 12);
		User u2 = new User(null, "João", 20, (float)35.6 , (float)1.65, (float)50, 12);
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
}
