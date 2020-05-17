package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.entity.Allergy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface AllergyMapper {

    AllergyMapper INSTANCE = Mappers.getMapper(AllergyMapper.class);

    AllergyDto allergyToAllergyDto(Allergy allergy);

    Allergy allergyDtoToAllergy(AllergyDto allergyDto);
}
