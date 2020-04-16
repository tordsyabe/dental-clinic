package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "procedures")
public class Procedure extends BaseEntity {
	
	private String description;
	private Integer cost;
	private Boolean paid;
	private String category;
	
	@OneToOne
	private Teeth teeth; 
	
	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;

	

	

}
