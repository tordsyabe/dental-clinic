package com.johnllave.dentalclinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Patient;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PatientMapper {
	
	PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

	PatientDto patientToPatientDto(Patient patient);

	Patient patientDtoToPatient(PatientDto patientDto);
}
