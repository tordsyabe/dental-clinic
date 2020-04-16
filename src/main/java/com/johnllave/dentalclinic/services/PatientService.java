package com.johnllave.dentalclinic.services;

import java.util.List;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Patient;

public interface PatientService {
	
	List<PatientDto> getPatients();
	
	Patient getPatientById(Long id);

	PatientDto savePatient(PatientDto patientDto);
}
