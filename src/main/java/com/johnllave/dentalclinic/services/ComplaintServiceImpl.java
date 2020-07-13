package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.ComplaintDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Complaint;
import com.johnllave.dentalclinic.mapper.ComplaintMapper;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.repository.ComplaintRepository;
import com.johnllave.dentalclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final PatientRepository patientRepository;
    private final ComplaintMapper complaintMapper;
    private final ComplaintRepository complaintRepository;
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @Autowired
    public ComplaintServiceImpl(PatientRepository patientRepository, PatientService patientService, ComplaintMapper complaintMapper, ComplaintRepository complaintRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
        this.complaintMapper = complaintMapper;
        this.complaintRepository = complaintRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public ComplaintDto saveComplaintByPatientId(String id, ComplaintDto complaintDto) {

        PatientDto patientDto = patientService.getPatientById(Long.parseLong(id));

        Complaint complaint = complaintMapper.complaintDtoToComplaint(complaintDto, new CycleAvoidingMappingContext());

        complaint.setPatient(patientMapper.patientDtoToPatient(patientDto, new CycleAvoidingMappingContext()));

        return complaintMapper.complaintToComplaintDto(complaintRepository.save(complaint), new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteComplaintById(String id) {
        complaintRepository.deleteById(Long.parseLong(id));
    }
}
