package com.johnllave.dentalclinic.services;

import java.util.List;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Patient;

public interface PatientService {
	
	List<PatientDto> getPatients();
	
	PatientDto getPatientById(String id);

	PatientDto savePatient(PatientDto patientDto);


}
