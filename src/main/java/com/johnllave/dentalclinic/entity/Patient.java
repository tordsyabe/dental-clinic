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

	private LocalDate dateCreated;
	private LocalDate dateUpdated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private Set<Complaint> complaints = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private Set<Procedure> procedures = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private Set<Allergy> allergies = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private Set<MedicalHistory> medicalHistories = new HashSet<>();

	public Patient addProcedure(Procedure procedure) {
		procedure.setPatient(this);
		this.procedures.add(procedure);
		return this;
	}

	public Patient addComplaint(Complaint complaint) {
		complaint.setPatient(this);
		this.complaints.add(complaint);
		return this;
	}

	public Patient addAllergy(Allergy allergy) {
		allergy.setPatient(this);
		this.allergies.add(allergy);
		return this;
	}

	public Patient addMedicalHistory(MedicalHistory medicalHistory) {
		medicalHistory.setPatient(this);
		this.medicalHistories.add(medicalHistory);
		return this;
	}

}
