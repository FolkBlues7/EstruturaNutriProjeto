package com.nutricao.estruturaDeDadosNutri.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_meal")
public class Meal implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "diet_id")
	@JsonIgnore
	private Diet diet;
	
	
	@ManyToMany
	@JoinTable(name = "meal_food",
	           joinColumns = @JoinColumn(name = "meal_id"),
	           inverseJoinColumns = @JoinColumn(name = "foods_id"))
	private List<Food> foods = new ArrayList<>();
	private boolean status;
	private LocalDateTime mealTime;
	
	//construtor padrão
	public Meal() {
		
	}

	//construtor
	public Meal(Long id, boolean status, LocalDateTime mealTime) {
		super();
		this.id = id;
		this.foods = new ArrayList<Food>();
		this.status = status;
		this.setMealTime(mealTime);
	}

	
	//getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	//hashcode e equals
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
		Meal other = (Meal) obj;
		return Objects.equals(id, other.id);
	}

	public LocalDateTime getMealTime() {
		return mealTime;
	}

	public void setMealTime(LocalDateTime mealTime) {
		this.mealTime = mealTime;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}
	
	
	
}
