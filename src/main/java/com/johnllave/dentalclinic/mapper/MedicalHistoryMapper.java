package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.MedicalHistoryDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.MedicalHistory;
import com.johnllave.dentalclinic.entity.Patient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PatientMapper.class, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MedicalHistoryMapper {

    MedicalHistory INSTANCE = Mappers.getMapper(MedicalHistory.class);

    @Mapping(target="dateCreated", source = "medicalHistory.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "medicalHistory.id")
    @Mapping(target = "patientDto", source = "medicalHistory.patient")
    MedicalHistoryDto medicalHistoryToMedicalHistoryDto(MedicalHistory medicalHistory, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target="dateCreated", source = "medicalHistoryDto.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "medicalHistoryDto.id")
    @Mapping(target = "patient", source = "medicalHistoryDto.patientDto")
    MedicalHistory medicalHistoryDtoToMedicalHistory(MedicalHistoryDto medicalHistoryDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
