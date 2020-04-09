package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

//	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	private LocalDate date;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visit")
	private Set<Procedure> procedures = new HashSet<>();
	
	public Visit addProcedure(Procedure procedure) {
		procedure.setVisit(this);
		this.procedures.add(procedure);
		return this;
	}
}
