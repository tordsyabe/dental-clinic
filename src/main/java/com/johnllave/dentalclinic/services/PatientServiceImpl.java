package com.johnllave.dentalclinic.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;
	private final ProcedureRepository procedureRepository;
	private final ProcedureMapper procedureMapper;
	

	public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, ProcedureRepository procedureRepository, ProcedureMapper procedureMapper) {

		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
		this.procedureRepository = procedureRepository;
		this.procedureMapper = procedureMapper;
	}


	@Override
	public List<PatientDto> getPatients() {
		
		List<PatientDto> patientsDto = new ArrayList<>();
		
		patientRepository.findAll().forEach(patient -> patientsDto.add(patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext())));

		patientsDto.forEach(patient -> patient.setAge(Period.between(LocalDate.parse(patient.getBirthDate()), LocalDate.now()).getYears()));
		
		return patientsDto;
	}


	@Override
	public PatientDto getPatientById(String id) {

		Patient patient = patientRepository.findByUuid(id);

		if(patient != null) {
			patient.setAge(Period.between(patient.getBirthDate(), LocalDate.now()).getYears());
		}

		PatientDto patientDto = patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext());


		return patientDto;

	}

	@Override
	public PatientDto savePatient(PatientDto patientDto) {

		Patient patient = patientMapper.patientDtoToPatient(patientDto, new CycleAvoidingMappingContext());

		if(patientDto.getUuid() == null) {
			patient.setDateCreated(LocalDate.now());
		}

		patient.setDateUpdated(LocalDate.now());

		Patient savePatient = patientRepository.save(patient);

		return patientMapper.patientToPatientDto(savePatient, new CycleAvoidingMappingContext());

	}

	@Override
	public void deletePatientById(String id) {

		Patient patient = patientRepository.findByUuid(id);

		patientRepository.delete(patient);

	}

}
