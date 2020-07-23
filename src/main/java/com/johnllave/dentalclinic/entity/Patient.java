package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", orphanRemoval = true)
	private List<MissingTooth> missingTeeth = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private List<Complaint> complaints = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private List<Procedure> procedures = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private List<Allergy> allergies = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private List<MedicalHistory> medicalHistories = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	private List<PatientDocument> documents = new ArrayList<>();


	public Patient addDocument(PatientDocument patientDocument) {
		patientDocument.setPatient(this);
		this.documents.add(patientDocument);
		return this;
	}

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

	public Patient addMissingTooth(MissingTooth missingTooth) {
		missingTooth.setPatient(this);
		this.missingTeeth.add(missingTooth);
		return this;
	}

}
