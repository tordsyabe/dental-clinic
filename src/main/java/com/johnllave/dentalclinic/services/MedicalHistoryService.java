package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MedicalHistoryDto;

public interface MedicalHistoryService {

    MedicalHistoryDto saveMedicalHistoryByPatientId(String id, MedicalHistoryDto medicalHistoryDto);

    void deleteMedicalHistory(String id);
}
