package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.ComplaintDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Complaint;
import com.johnllave.dentalclinic.entity.Patient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PatientMapper.class, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @Mapping(target="dateCreated", source = "complaint.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "complaint.id")
    @Mapping(target = "patientDto", source = "complaint.patient")
    ComplaintDto complaintToComplaintDto(Complaint complaint, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target="dateCreated", source = "complaintDto.dateCreated",dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "complaintDto.id")
    @Mapping(target = "patient", source = "complaintDto.patientDto")
    Complaint complaintDtoToComplaint(ComplaintDto complaintDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
