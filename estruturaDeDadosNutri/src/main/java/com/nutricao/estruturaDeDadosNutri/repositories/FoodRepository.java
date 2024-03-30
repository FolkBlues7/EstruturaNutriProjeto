package com.nutricao.estruturaDeDadosNutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricao.estruturaDeDadosNutri.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
