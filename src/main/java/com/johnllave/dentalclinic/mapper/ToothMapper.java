package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.ToothDto;
import com.johnllave.dentalclinic.entity.Tooth;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ToothMapper {

    ToothMapper INSTANCE = Mappers.getMapper(ToothMapper.class);

    @Mapping(target = "id", source = "tooth.id")
    ToothDto teethToTeethDto(Tooth tooth, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "id", source = "toothDto.id")
    Tooth teethDtoToTeeth(ToothDto toothDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
