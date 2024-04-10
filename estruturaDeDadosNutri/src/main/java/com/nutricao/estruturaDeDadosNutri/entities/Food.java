package com.nutricao.estruturaDeDadosNutri.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.nutricao.estruturaDeDadosNutri.structures.DataStructures.DoublyLinkedList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "food")
public class Food implements Serializable, Comparable<Food> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private float calories;
	private float weight;
	private float carbohydrates;
	private float proteins;
	private float lipids;
	
	@ManyToMany(mappedBy = "foods")
	private List<Meal> meals = new DoublyLinkedList<>();
	
	//construtor vazio
	public Food() {
		
	}

	//construtor padrão
	public Food(Long id, String name, float calories, float weight, float carbohydrates, float proteins, float lipids) {
		super();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.weight = weight;
		this.carbohydrates = carbohydrates;
		this.proteins = proteins;
		this.lipids = lipids;
	}
	
	//getters e setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public float getProteins() {
		return proteins;
	}

	public void setProteins(float proteins) {
		this.proteins = proteins;
	}

	public float getLipids() {
		return lipids;
	}

	public void setLipids(float lipids) {
		this.lipids = lipids;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//hashcode e equals
	@Override
	public int hashCode() {
		return Objects.hash(weight, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		return Float.floatToIntBits(weight) == Float.floatToIntBits(other.weight)
		&& Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Food other) {
		return this.name.compareTo(other.name);
	}

}
