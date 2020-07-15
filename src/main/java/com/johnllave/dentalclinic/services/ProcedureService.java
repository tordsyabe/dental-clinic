package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;

import java.util.List;

public interface ProcedureService {

    List<ProcedureDto> getProcedures();

    ProcedureDto getProcedureById(String id);

    List<ProcedureDto> getProceduresByDate(String date);

    ProcedureDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto);

    List<ProcedureDto> getProceduresByPatientIdAndToothId(String patientId, String toothId);

    void deleteProcedure(String id);
}
