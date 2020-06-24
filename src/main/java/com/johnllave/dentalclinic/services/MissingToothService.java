package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.dto.TeethDto;

public interface MissingToothService {

    MissingToothDto addMissingToothByPatientId(String patientId, String toothId);

    void deleteMissingTooth(String toothId);
}
