package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity
@Table(name = "procedures")
@ToString
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
