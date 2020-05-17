package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.MedicalHistoryDto;
import com.johnllave.dentalclinic.entity.MedicalHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MedicalHistoryMapper {

    MedicalHistory INSTANCE = Mappers.getMapper(MedicalHistory.class);

    MedicalHistoryDto medicalHistoryToMedicalHistoryDto(MedicalHistory medicalHistory);

    MedicalHistory medicalHistoryDtoToMedicalHistory(MedicalHistoryDto medicalHistoryDto);
}
