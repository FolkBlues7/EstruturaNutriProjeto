package com.nutricao.estruturaDeDadosNutri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricao.estruturaDeDadosNutri.entities.Diet;

public interface DietRepository extends JpaRepository<Diet, Long> {

}
