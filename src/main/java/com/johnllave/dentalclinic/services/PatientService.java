package com.johnllave.dentalclinic.services;

import java.util.Set;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Patient;

public interface PatientService {
	
	Set<PatientDto> getPatients();
	
	Patient getPatientById(Long id);

	PatientDto savePatient(PatientDto patientDto);
}
