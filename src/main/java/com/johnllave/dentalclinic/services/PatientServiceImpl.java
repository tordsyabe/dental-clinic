package com.johnllave.dentalclinic.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	
	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;
	

	public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {

		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}


	@Override
	public List<PatientDto> getPatients() {
		
		List<PatientDto> patients = new ArrayList<>();
		
		patientRepository.findAll().forEach(patient -> patients.add(patientMapper.patientToPatientDto(patient)));

		patients.forEach(patient -> patient.setAge(Period.between(LocalDate.parse(patient.getBirthDate()), LocalDate.now()).getYears()));
		
		return patients;
	}


	@Override
	public Patient getPatientById(Long id) {

		Patient patient = patientRepository.findById(id).orElse(null);

		if(patient != null) {
			patient.setAge(Period.between(patient.getBirthDate(), LocalDate.now()).getYears());
		}


		return patient;

	}

	@Override
	public PatientDto savePatient(PatientDto patientDto) {

		if(patientDto.getImage().equals("")) {
			patientDto.setImage(null);
		}

		Patient patient = patientMapper.patientDtoToPatient(patientDto);

		Patient savePatient = patientRepository.save(patient);

		return patientMapper.patientToPatientDto(savePatient);

	}

}
