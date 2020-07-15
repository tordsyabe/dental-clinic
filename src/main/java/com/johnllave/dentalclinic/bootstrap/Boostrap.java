package com.johnllave.dentalclinic.bootstrap;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import com.johnllave.dentalclinic.entity.*;
import com.johnllave.dentalclinic.repository.ComplaintRepository;
import com.johnllave.dentalclinic.repository.ToothRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.johnllave.dentalclinic.repository.PatientRepository;

@Component
public class Boostrap implements ApplicationListener<ContextRefreshedEvent> {

	private final ToothRepository toothRepository;
	private final PatientRepository patientRepository;
	private final ComplaintRepository complaintRepository;

	public Boostrap(ToothRepository toothRepository, PatientRepository patientRepository, ComplaintRepository complaintRepository) {

		this.toothRepository = toothRepository;
		this.patientRepository = patientRepository;
		this.complaintRepository = complaintRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		patientRepository.saveAll(loadPatients());

	}

	private List<Patient> loadPatients() {

		List<Patient> patients = new ArrayList<Patient>();

		Optional<Tooth> upperRight3rdMolar = toothRepository.findByDescription("upper right 3rd molar");

		if (!upperRight3rdMolar.isPresent()) {
			throw new RuntimeException("Expected Teeth Not Found");
		}

		Optional<Tooth> upperLeftCentralIncisor = toothRepository.findByDescription("upper left central incisor");

		if (!upperLeftCentralIncisor.isPresent()) {
			throw new RuntimeException("Expected Teeth Not Found");
		}

		Optional<Tooth> lowerright2ndBicuspid = toothRepository.findByDescription("lower right 2nd bicuspid");

		if (!lowerright2ndBicuspid.isPresent()) {
			throw new RuntimeException("Expected Teeth Not Found");
		}

		MissingTooth missingTooth1 = new MissingTooth();
		missingTooth1.setTooth(upperLeftCentralIncisor.orElse(null));

		MissingTooth missingTooth2 = new MissingTooth();
		missingTooth2.setTooth(lowerright2ndBicuspid.orElse(null));

//		SET PATIENT DETAILS
		Patient hillary = new Patient();

		hillary.setFirstName("Hillary Joevette");
		hillary.setLastName("Tan");
		hillary.setBirthDate(LocalDate.of(1996, 3, 29));
		hillary.setGender("female");
		hillary.setPhone("09211232323");
		hillary.setEmail("hillary@gmail.com");
		hillary.setAddress("Celine Homes Fortune Towne");
		hillary.setCity("Bacolod");
		hillary.setProvince("Negros Occidental");
		hillary.addMissingTooth(missingTooth1);
		hillary.addMissingTooth(missingTooth2);

		Patient john = new Patient();

		john.setFirstName("John");
		john.setLastName("Llave");
		john.setBirthDate(LocalDate.of(1990, Month.SEPTEMBER, 14));
		john.setGender("male");
		john.setPhone("09211232323");
		john.setEmail("john@gmail.com");
		john.setAddress("Rodriguez Avenue Barangay 35");
		john.setCity("Bacolod");
		john.setProvince("Negros Occidental");

//		SET PROCEDURE FOR FIRST VISIT
		Procedure procedure1 = new Procedure();

		procedure1.setCategory("Surgery");
		procedure1.setCost(960);
		procedure1.setDescription("Permanent tooth extraction");
		procedure1.setPaid(false);
		procedure1.setTooth(upperRight3rdMolar.orElse(null));
		procedure1.setDateCreated(LocalDate.of(2020, 3, 2));

		Procedure procedure2 = new Procedure();

		procedure2.setCategory("Restoration");
		procedure2.setCost(960);
		procedure2.setDescription("Restoration of teeth");
		procedure2.setPaid(false);
		procedure2.setTooth(upperLeftCentralIncisor.orElse(null));
		procedure2.setDateCreated(LocalDate.now());



		Procedure procedure3 = new Procedure();

		procedure3.setCategory("Root Canal");
		procedure3.setCost(960);
		procedure3.setDescription("Canalization of the teeth");
		procedure3.setPaid(true);
		procedure3.setTooth(lowerright2ndBicuspid.orElse(null));
		procedure3.setDateCreated(LocalDate.of(2020, 1, 1));

		Procedure procedure4 = new Procedure();

		procedure4.setCategory("Prosthetics");
		procedure4.setCost(3000);
		procedure4.setDescription("Compisite veneers");
		procedure4.setPaid(true);
		procedure4.setTooth(lowerright2ndBicuspid.orElse(null));
		procedure4.setDateCreated(LocalDate.of(2020, 4, 1));

		Complaint complaint1 = new Complaint();
		complaint1.setDescription("There was a bleeding gum");
		complaint1.setDateCreated(LocalDate.now());

		Complaint complaint2 = new Complaint();
		complaint2.setDescription("Walay kwarta pambayad");
		complaint2.setDateCreated(LocalDate.of(2020, 1, 2));

		Complaint complaint3 = new Complaint();
		complaint3.setDescription("This is the complaint saved from complaint side");
		complaint3.setDateCreated(LocalDate.of(2020, 1, 2));

		Allergy allergy1 = new Allergy("Allergic to anesthesia");
		Allergy allergy2 = new Allergy("Allergic to inhalers");
		Allergy allergy3 = new Allergy("Allergic to ibuprofen ");

		MedicalHistory medicalHistory1 = new MedicalHistory("Heart surgery year 2007");
		MedicalHistory medicalHistory2 = new MedicalHistory("Skull fracture year 1991");

		hillary.addProcedure(procedure1);
		hillary.addProcedure(procedure2);
		hillary.addProcedure(procedure3);
		hillary.addAllergy(allergy1);
		hillary.addMedicalHistory(medicalHistory1);


		john.addAllergy(allergy2);
		john.addAllergy(allergy3);
		john.addProcedure(procedure4);
		john.addComplaint(complaint1);
		john.addComplaint(complaint2);
		john.addMedicalHistory(medicalHistory2);
		complaint3.setPatient(hillary);

		patients.add(hillary);
		patients.add(john);

		complaintRepository.save(complaint3);



		return patients;
	}

}
