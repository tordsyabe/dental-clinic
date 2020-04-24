package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;

import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient extends Person {

	@Lob
	private String image;
	private LocalDate birthDate;
	private String gender;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String province;

	private int age;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private Set<Procedure> procedures = new HashSet<>();

	public Patient addProcedure(Procedure procedure) {
		procedure.setPatient(this);
		this.procedures.add(procedure);
		return this;
	}

}
