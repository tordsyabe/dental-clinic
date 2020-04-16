package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.TeethDto;
import com.johnllave.dentalclinic.entity.Teeth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TeethMapper {

    TeethMapper INSTANCE = Mappers.getMapper(TeethMapper.class);

    TeethDto teethToTeethDto(Teeth teeth);

    Teeth teethDtoToTeeth(TeethDto teethDto);
}
