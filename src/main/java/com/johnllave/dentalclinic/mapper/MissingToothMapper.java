package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.entity.MissingTooth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MissingToothMapper {

    MissingToothMapper INSTANCE = Mappers.getMapper(MissingToothMapper.class);

    MissingTooth missingToothDtoToMissingTooth(MissingToothDto missingToothDto);

    MissingToothDto missingToothToMissingToothDto(MissingTooth missingTooth);
}
