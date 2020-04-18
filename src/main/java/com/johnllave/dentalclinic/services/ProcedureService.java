package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Patient;

public interface ProcedureService {

    PatientDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto);
}
