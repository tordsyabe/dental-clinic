package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.entity.MissingTooth;
import com.johnllave.dentalclinic.entity.Patient;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PatientMapper.class, ToothMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MissingToothMapper {

    MissingToothMapper INSTANCE = Mappers.getMapper(MissingToothMapper.class);

    @Mapping(target = "id", source = "missingTooth.id")
    @Mapping(target = "patientDto", source = "missingTooth.patient")
    @Mapping(target = "toothDto", source = "missingTooth.tooth")
    MissingToothDto missingToothToMissingToothDto(MissingTooth missingTooth, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "id", source = "missingToothDto.id")
    @Mapping(target = "patient", source = "missingToothDto.patientDto")
    @Mapping(target = "tooth", source = "missingToothDto.toothDto")
    MissingTooth missingToothDtoToMissingTooth(MissingToothDto missingToothDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


}
