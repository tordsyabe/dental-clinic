package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "procedures")
public class Procedure extends BaseEntity {

	private LocalDate dateCreated;
	private String description;
	private Integer cost;
	private Boolean paid;
	private String category;

	
	@OneToOne
	private Teeth teeth;

	@OneToOne(cascade = CascadeType.ALL)
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

}
