package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Allergy;
import com.johnllave.dentalclinic.entity.Patient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PatientMapper.class,unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AllergyMapper {

    AllergyMapper INSTANCE = Mappers.getMapper(AllergyMapper.class);

    @Mapping(target = "dateCreated", source = "allergy.dateCreated", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "allergy.id")
    @Mapping(target = "patientDto", source = "allergy.patient")
    AllergyDto allergyToAllergyDto(Allergy allergy, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target="dateCreated", source = "allergyDto.dateCreated", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "allergyDto.id")
    @Mapping(target = "patient", source = "allergyDto.patientDto")
    Allergy allergyDtoToAllergy(AllergyDto allergyDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
