package com.johnllave.dentalclinic.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Tooth  extends BaseEntity{
	
	private String description;
	private String category;
	private Integer number;

	public Tooth() {
		// TODO Auto-generated constructor stub
	}

	public Tooth(String description, String category, Integer number) {
		this.description = description;
		this.category = category;
		this.number = number;
	}
	
	
}
