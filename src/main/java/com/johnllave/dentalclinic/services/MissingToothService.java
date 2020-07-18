package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MissingToothDto;

public interface MissingToothService {

    MissingToothDto addMissingToothByPatientId(String patientId, String toothId);

    void deleteMissingTooth(String id);
}
