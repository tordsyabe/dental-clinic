package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.TeethDto;
import com.johnllave.dentalclinic.entity.Teeth;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TeethMapper {

    TeethMapper INSTANCE = Mappers.getMapper(TeethMapper.class);

    @Mapping(target = "id", source = "teeth.id")
    TeethDto teethToTeethDto(Teeth teeth, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "id", source = "teethDto.id")
    Teeth teethDtoToTeeth(TeethDto teethDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
