package com.johnllave.dentalclinic.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Teeth  extends BaseEntity{
	
	private String description;
	private String category;
	private Integer number;
	
	
	
	@Override
	public String toString() {
		return "Teeth [description=" + description + ", category=" + category + ", number=" + number + "]";
	}

	public Teeth() {
		// TODO Auto-generated constructor stub
	}

	public Teeth(String description, String category, Integer number) {
		this.description = description;
		this.category = category;
		this.number = number;
	}
	
	
}
