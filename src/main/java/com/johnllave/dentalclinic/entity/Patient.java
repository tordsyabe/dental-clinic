package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "patients")
public class Patient extends Person {

	@Lob
	private String image;
	private LocalDate birthDate;
	private Integer age;
	private String gender;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String province;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
	private Set<Visit> visits = new HashSet<>();

	public Patient addVisit(Visit visit) {
		visit.setPatient(this);
		this.visits.add(visit);
		return this;
	}

}
