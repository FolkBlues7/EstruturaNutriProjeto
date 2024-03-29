package com.nutricao.estruturaDeDadosNutri.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	//implementando a serialização
	private static final long serialVersionUID = 1L;
	//atributos como no diagrama
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //atributo não especificado no diagrama
	private String name;
	private int age;
	private float weight;
	private float height;
	private float bodyWeight;
	private float imc;

	public User() {

	}
	//private diet = nova dieta e tals;
	

	//Fazer o tratamento de exceções nos getter e setter, pois apenas gera-los não garante encapsulamento
	public String getName() {
		return name;
	}

	public User(Long id, String name, int age, float weight, float height, float bodyWeight, float imc) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.bodyWeight = bodyWeight;
		this.imc = imc;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getBodyWeight() {
		return bodyWeight;
	}
	public void setBodyWeight(float bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	public float getImc() {
		return imc;
	}
	public void setImc(float imc) {
		this.imc = imc;
	}
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	//método de dar update no IMC, quando tiver o BD
	//public updateIMC(){}
}
