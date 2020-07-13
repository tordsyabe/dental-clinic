package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PatientMapper.class, TeethMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProcedureMapper {

    ProcedureMapper INSTANCE = Mappers.getMapper(ProcedureMapper.class);

    @Mapping(target="dateCreated", source = "procedure.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "procedure.id")
    @Mapping(target = "cost", source = "procedure.cost")
    @Mapping(target = "invoiceDto", source = "procedure.invoice")
    @Mapping(target = "patientDto", source = "procedure.patient")
    ProcedureDto procedureToProcedureDto(Procedure procedure, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target="dateCreated", source = "procedureDto.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "procedureDto.id")
    @Mapping(target = "cost", source = "procedureDto.cost")
    @Mapping(target = "invoice", source = "procedureDto.invoiceDto")
    @Mapping(target = "patient", source = "procedureDto.patientDto")
    Procedure procedureDtoToProcedure(ProcedureDto procedureDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
