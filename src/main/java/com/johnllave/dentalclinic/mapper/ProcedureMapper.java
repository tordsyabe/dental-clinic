package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Procedure;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PatientMapper.class, ToothMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProcedureMapper {

    ProcedureMapper INSTANCE = Mappers.getMapper(ProcedureMapper.class);

    @Mapping(target="dateCreated", source = "procedure.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "procedure.id")
    @Mapping(target = "cost", source = "procedure.cost")
    @Mapping(target = "invoiceDto", source = "procedure.invoice")
    @Mapping(target = "patientDto", source = "procedure.patient")
    @Mapping(target = "toothDto", source = "procedure.tooth")
    ProcedureDto procedureToProcedureDto(Procedure procedure, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target="dateCreated", source = "procedureDto.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "procedureDto.id")
    @Mapping(target = "cost", source = "procedureDto.cost")
    @Mapping(target = "invoice", source = "procedureDto.invoiceDto")
    @Mapping(target = "patient", source = "procedureDto.patientDto")
    @Mapping(target = "tooth", source = "procedureDto.toothDto")
    Procedure procedureDtoToProcedure(ProcedureDto procedureDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
