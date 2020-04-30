package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.ComplaintDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Complaint;
import com.johnllave.dentalclinic.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    ComplaintDto complaintToComplaintDto(Complaint complaint);

    Complaint complaintDtoToComplaint(ComplaintDto complaintDto);
}
