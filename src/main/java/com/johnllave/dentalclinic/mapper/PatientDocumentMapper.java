package com.johnllave.dentalclinic.mapper;


import com.johnllave.dentalclinic.dto.PatientDocumentDto;
import com.johnllave.dentalclinic.entity.PatientDocument;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PatientMapper.class, componentModel = "spring")
public interface PatientDocumentMapper {

    PatientDocumentMapper INSTANCE = Mappers.getMapper(PatientDocumentMapper.class);

    @Mapping(target = "dateUploaded", source = "patientDocument.dateUploaded", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "patientDocument.id")
    @Mapping(target = "patientDto", source = "patientDocument.patient")
    PatientDocumentDto entityToDto(PatientDocument patientDocument,
                                   @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "dateUploaded", source = "patientDocumentDto.dateUploaded", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "patientDocumentDto.id")
    @Mapping(target = "patient", source = "patientDocumentDto.patientDto")
    PatientDocument dtoToEntity(PatientDocumentDto patientDocumentDto,
                                @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


}
