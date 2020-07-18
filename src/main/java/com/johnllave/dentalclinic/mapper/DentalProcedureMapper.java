package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.DentalProcedureDto;
import com.johnllave.dentalclinic.entity.DentalProcedure;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DentalProcedureCategoryMapper.class, componentModel = "spring")
public interface DentalProcedureMapper {

    DentalProcedureMapper INSTANCE = Mappers.getMapper(DentalProcedureMapper.class);


    @Mapping(target = "cost", source = "dentalProcedure.cost")
    @Mapping(target = "dentalProcedureCategoryDto", source = "dentalProcedure.dentalProcedureCategory")
    DentalProcedureDto entityToDto(DentalProcedure dentalProcedure,
                                   @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "cost", source = "dentalProcedureDto.cost")
    @Mapping(target = "dentalProcedureCategory", source = "dentalProcedureDto.dentalProcedureCategoryDto")
    DentalProcedure dtoToEntity(DentalProcedureDto dentalProcedureDto,
                                @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
