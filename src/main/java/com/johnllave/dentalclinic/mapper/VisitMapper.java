package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.VisitDto;
import com.johnllave.dentalclinic.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VisitMapper {

    VisitMapper INSTANCE = Mappers.getMapper(VisitMapper.class);

    VisitDto visitToVisitDto(Visit visit);

    Visit visitDtoToVisit(VisitDto visitDto);
}
