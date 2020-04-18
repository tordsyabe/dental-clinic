package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.entity.Visit;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final TeethService teethService;
    private final PatientService patientService;
    private final ProcedureMapper procedureMapper;
    private final PatientMapper patientMapper;

    public ProcedureServiceImpl(TeethService teethService, PatientService patientService, ProcedureMapper procedureMapper, PatientMapper patientMapper) {
        this.teethService = teethService;
        this.patientService = patientService;
        this.procedureMapper = procedureMapper;

        this.patientMapper = patientMapper;
    }


    @Override
    public PatientDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto) {
        Teeth teethToSave = teethService.getTeethById(Long.parseLong(procedureDto.getTeethId()));

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));

        procedureDto.setPaid(false);

        Procedure convertedProcedure = procedureMapper.procedureDtoToProcedure(procedureDto);

        convertedProcedure.setTeeth(teethToSave);

        Visit visit = new Visit();

        visit.addProcedure(convertedProcedure);
        visit.setDate(LocalDate.now());
        patient.addVisit(visit);

        PatientDto patientDto = patientMapper.patientToPatientDto(patient);

        return patientService.savePatient(patientDto);
    }
}
