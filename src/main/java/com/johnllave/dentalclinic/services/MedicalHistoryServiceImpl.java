package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MedicalHistoryDto;
import com.johnllave.dentalclinic.entity.MedicalHistory;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.MedicalHistoryMapper;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final MedicalHistoryMapper medicalHistoryMapper;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, PatientService patientService, PatientMapper patientMapper, MedicalHistoryMapper medicalHistoryMapper) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.medicalHistoryMapper = medicalHistoryMapper;
    }

    @Override
    public MedicalHistoryDto saveMedicalHistoryByPatientId(String id, MedicalHistoryDto medicalHistoryDto) {

        Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(Long.parseLong(id)), new CycleAvoidingMappingContext());

        MedicalHistory medicalHistory = medicalHistoryMapper.medicalHistoryDtoToMedicalHistory(medicalHistoryDto, new CycleAvoidingMappingContext());

        medicalHistory.setPatient(patient);

        return medicalHistoryMapper.medicalHistoryToMedicalHistoryDto(medicalHistoryRepository.save(medicalHistory), new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteMedicalHistoryById(String id) {
        medicalHistoryRepository.deleteById(Long.parseLong(id));
    }
}
