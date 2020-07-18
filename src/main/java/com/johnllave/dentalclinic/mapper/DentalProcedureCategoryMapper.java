package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.DentalProcedureCategoryDto;
import com.johnllave.dentalclinic.entity.DentalProcedureCategory;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DentalProcedureMapper.class},componentModel = "spring")
public interface DentalProcedureCategoryMapper {

    DentalProcedureCategoryMapper INSTANCE = Mappers.getMapper(DentalProcedureCategoryMapper.class);

    @Mapping(target = "dentalProceduresDto", source = "dentalProcedureCategory.dentalProcedures")
    DentalProcedureCategoryDto entityToDto(DentalProcedureCategory dentalProcedureCategory,
                                           @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "dentalProcedures", source = "dentalProcedureCategoryDto.dentalProceduresDto")
    DentalProcedureCategory dtoToEntity(DentalProcedureCategoryDto dentalProcedureCategoryDto,
                                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
