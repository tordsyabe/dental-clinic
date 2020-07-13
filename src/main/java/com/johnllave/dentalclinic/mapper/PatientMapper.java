package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.*;
import com.johnllave.dentalclinic.entity.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProcedureMapper.class, AllergyMapper.class, ComplaintMapper.class, MedicalHistoryMapper.class,
		MissingToothMapper.class}
		,unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PatientMapper {
	
	PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

	@Mapping(target="dateCreated", source = "patient.dateCreated",dateFormat = "yyyy-MM-dd")
	@Mapping(target="dateUpdated", source = "patient.dateUpdated",dateFormat = "yyyy-MM-dd")
	@Mapping(target="birthDate", source = "patient.birthDate",dateFormat = "yyyy-MM-dd")
	@Mapping(target = "id", source = "patient.id")
	@Mapping(target = "complaintsDto", source = "patient.complaints")
	@Mapping(target = "proceduresDto", source = "patient.procedures")
	@Mapping(target = "allergiesDto", source = "patient.allergies")
	@Mapping(target = "medicalHistoriesDto", source = "patient.medicalHistories")
	@Mapping(target = "missingTeethDto", source = "patient.missingTeeth")
	PatientDto patientToPatientDto(Patient patient, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

	@Mapping(target="dateCreated", source = "patientDto.dateCreated",dateFormat = "yyyy-MM-dd")
	@Mapping(target="dateUpdated", source = "patientDto.dateUpdated",dateFormat = "yyyy-MM-dd")
	@Mapping(target="birthDate", source = "patientDto.birthDate",dateFormat = "yyyy-MM-dd")
	@Mapping(target = "id", source = "patientDto.id")
	@Mapping(target = "complaints", source = "patientDto.complaintsDto")
	@Mapping(target = "procedures", source = "patientDto.proceduresDto")
	@Mapping(target = "allergies", source = "patientDto.allergiesDto")
	@Mapping(target = "medicalHistories", source = "patientDto.medicalHistoriesDto")
	@Mapping(target = "missingTeeth", source = "patientDto.missingTeethDto")
	Patient patientDtoToPatient(PatientDto patientDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
