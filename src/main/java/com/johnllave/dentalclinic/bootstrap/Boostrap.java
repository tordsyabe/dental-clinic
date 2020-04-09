package com.johnllave.dentalclinic.bootstrap;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.entity.Visit;
import com.johnllave.dentalclinic.repository.PatientRepository;
import com.johnllave.dentalclinic.repository.TeethRepository;

@Component
public class Boostrap implements ApplicationListener<ContextRefreshedEvent> {

	private final TeethRepository teethRepository;
	private final PatientRepository patientRepository;

	public Boostrap(TeethRepository teethRepository, PatientRepository patientRepository) {

		this.teethRepository = teethRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		patientRepository.saveAll(loadPatients());

	}

	private List<Patient> loadPatients() {

		List<Patient> patients = new ArrayList<Patient>();

		Optional<Teeth> upperRight3rdMolar = teethRepository.findByDescription("upper right 3rd molar");

		if (!upperRight3rdMolar.isPresent()) {
			throw new RuntimeException("Expected Teeth Not Found");
		}

		Optional<Teeth> upperLeftCentralIncisor = teethRepository.findByDescription("upper left central incisor");

		if (!upperLeftCentralIncisor.isPresent()) {
			throw new RuntimeException("Expected Teeth Not Found");
		}
		
		Optional<Teeth> lowerright2ndBicuspid = teethRepository.findByDescription("lower right 2nd bicuspid");

		if (!lowerright2ndBicuspid.isPresent()) {
			throw new RuntimeException("Expected Teeth Not Found");
		}

//		SET PATIENT DETAILS
		Patient hillary = new Patient();

		hillary.setFirstName("Hillary Joevette");
		hillary.setLastName("Tan");
		hillary.setBirthDate(LocalDate.of(1996, 3, 29));
		hillary.setAge(26);
		hillary.setGender("female");
		hillary.setPhone("09211232323");
		hillary.setEmail("hillary@gmail.com");
		hillary.setAddress("Celine Homes Fortune Towne");
		hillary.setCity("Bacolod");
		hillary.setProvince("Negros Occidental");
		
		Patient john = new Patient();

		john.setFirstName("John");
		john.setLastName("Llave");
		john.setBirthDate(LocalDate.of(1990, Month.SEPTEMBER, 14));
		john.setAge(29);
		john.setGender("male");
		john.setPhone("09211232323");
		john.setEmail("john@gmail.com");
		john.setAddress("Rodriguez Avenue Barangay 35");
		john.setCity("Bacolod");
		john.setProvince("Negros Occidental");
		

//		SET PATIENT FIRST VISIT
		Visit firstVisit = new Visit();
		Visit secondVisit = new Visit();
		
		Visit thirdVisit = new Visit();

		firstVisit.setDate(LocalDate.now());
		
		secondVisit.setDate(LocalDate.of(2020, 2, 14));
		
		thirdVisit.setDate(LocalDate.of(2020, 3, 28));

//		SET PROCEDURE FOR FIRST VISIT
		Procedure procedure1Of1stVisit = new Procedure();

		procedure1Of1stVisit.setCategory("Surgery");
		procedure1Of1stVisit.setCost(960);
		procedure1Of1stVisit.setDescription("Permanent tooth extraction");
		procedure1Of1stVisit.setPaid(false);
		procedure1Of1stVisit.setTeeth(upperRight3rdMolar.orElse(null));
		
		Procedure procedure2Of1stVisit = new Procedure();
		
		procedure2Of1stVisit.setCategory("Restoration");
		procedure2Of1stVisit.setCost(960);
		procedure2Of1stVisit.setDescription("Restoration of teeth");
		procedure2Of1stVisit.setPaid(false);
		procedure2Of1stVisit.setTeeth(upperLeftCentralIncisor.orElse(null));

		
		
		Procedure procedure3Of2ndVisit = new Procedure();
		
		procedure3Of2ndVisit.setCategory("Root Canal");
		procedure3Of2ndVisit.setCost(960);
		procedure3Of2ndVisit.setDescription("Canalization of the teeth");
		procedure3Of2ndVisit.setPaid(true);
		procedure3Of2ndVisit.setTeeth(lowerright2ndBicuspid.orElse(null));
		
		Procedure procedure1Of3ndVisit = new Procedure();
		
		procedure1Of3ndVisit.setCategory("Prosthetics");
		procedure1Of3ndVisit.setCost(3000);
		procedure1Of3ndVisit.setDescription("Compisite veneers");
		procedure1Of3ndVisit.setPaid(true);
		procedure1Of3ndVisit.setTeeth(lowerright2ndBicuspid.orElse(null));
	
		firstVisit.addProcedure(procedure1Of1stVisit);
		firstVisit.addProcedure(procedure2Of1stVisit);
		secondVisit.addProcedure(procedure3Of2ndVisit);
		thirdVisit.addProcedure(procedure1Of3ndVisit);
		hillary.addVisit(firstVisit);
		hillary.addVisit(secondVisit);
		hillary.addVisit(thirdVisit);
		
		
		patients.add(hillary);
		patients.add(john);

		return patients;
	}

}
