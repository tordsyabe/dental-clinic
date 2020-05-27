package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MedicalHistoryDto;
import com.johnllave.dentalclinic.entity.MedicalHistory;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.MedicalHistoryMapper;
import com.johnllave.dentalclinic.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientService patientService;
    private final MedicalHistoryMapper medicalHistoryMapper;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, PatientService patientService, MedicalHistoryMapper medicalHistoryMapper) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientService = patientService;
        this.medicalHistoryMapper = medicalHistoryMapper;
    }

    @Override
    public MedicalHistoryDto saveMedicalHistoryByPatientId(String id, MedicalHistoryDto medicalHistoryDto) {

        Patient patient = patientService.getPatientById(Long.parseLong(id));

        MedicalHistory medicalHistory = medicalHistoryMapper.medicalHistoryDtoToMedicalHistory(medicalHistoryDto);

        medicalHistory.setPatient(patient);

        return medicalHistoryMapper.medicalHistoryToMedicalHistoryDto(medicalHistoryRepository.save(medicalHistory));
    }

    @Override
    public void deleteMedicalHistoryById(String id) {
        medicalHistoryRepository.deleteById(Long.parseLong(id));
    }
}
