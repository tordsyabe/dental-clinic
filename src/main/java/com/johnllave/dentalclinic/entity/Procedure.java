package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import static javax.persistence.CascadeType.*;

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
	private Tooth tooth;

	@OneToOne(cascade = ALL, mappedBy = "procedure")
	private Invoice invoice;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

}
