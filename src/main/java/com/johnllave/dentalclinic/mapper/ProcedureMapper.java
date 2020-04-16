package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Procedure;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProcedureMapper {

    ProcedureMapper INSTANCE = Mappers.getMapper(ProcedureMapper.class);

    ProcedureDto procedureToProcedureDto(Procedure procedure);

    Procedure procedureDtoToProcedure(ProcedureDto procedureDto);


}
