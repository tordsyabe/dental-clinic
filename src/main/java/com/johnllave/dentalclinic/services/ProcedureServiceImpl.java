package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final TeethService teethService;
    private final PatientService patientService;
    private final ProcedureMapper procedureMapper;
    private final PatientMapper patientMapper;
    private final ProcedureRepository procedureRepository;

    public ProcedureServiceImpl(TeethService teethService, PatientService patientService, ProcedureMapper procedureMapper, PatientMapper patientMapper, ProcedureRepository procedureRepository) {
        this.teethService = teethService;
        this.patientService = patientService;
        this.procedureMapper = procedureMapper;

        this.patientMapper = patientMapper;
        this.procedureRepository = procedureRepository;
    }


    @Override
    public PatientDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto) {
        Teeth teethToSave = teethService.getTeethById(Long.parseLong(procedureDto.getTeethId()));

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));

        procedureDto.setPaid(false);

        Procedure procedure = procedureMapper.procedureDtoToProcedure(procedureDto);

        procedure.setTeeth(teethToSave);
        procedure.setDate(LocalDate.now());

        patient.addProcedure(procedure);

        PatientDto patientDto = patientMapper.patientToPatientDto(patient);

        return patientService.savePatient(patientDto);
    }

}
