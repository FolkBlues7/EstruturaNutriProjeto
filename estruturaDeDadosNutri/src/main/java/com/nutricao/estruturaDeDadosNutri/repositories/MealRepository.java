package com.nutricao.estruturaDeDadosNutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricao.estruturaDeDadosNutri.entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {

}
