package com.nutricao.estruturaDeDadosNutri.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "diet")
public class Diet implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private List<Meal> meals = new ArrayList<>();

	public Diet() {

	}

	public Diet(Long id, List<Meal> meals) {
		super();
		this.id = id;
	//	this.meals = meals;
	}

	//public List<Meal> getMeals() {
	//	return meals;
	//}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diet other = (Diet) obj;
		return Objects.equals(id, other.id);
	}

}
