package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.ComplaintDto;
import com.johnllave.dentalclinic.entity.Complaint;

public interface ComplaintService {

    ComplaintDto saveComplaintByPatientId(String id, ComplaintDto complaintDto);

    void deleteComplaint(String id);
}
