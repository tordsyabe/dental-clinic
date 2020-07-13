package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.entity.MissingTooth;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.MissingToothMapper;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.TeethMapper;
import com.johnllave.dentalclinic.repository.MissingToothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissingToothServiceImpl implements MissingToothService {


    private final TeethMapper teethMapper;
    private final PatientService patientService;
    private final MissingToothRepository missingToothRepository;
    private final MissingToothMapper missingToothMapper;
    private final TeethService teethService;
    private final PatientMapper patientMapper;



    @Autowired
    public MissingToothServiceImpl(TeethMapper teethMapper, PatientService patientService, MissingToothRepository missingToothRepository, MissingToothMapper missingToothMapper, TeethService teethService, PatientMapper patientMapper) {
        this.teethMapper = teethMapper;
        this.patientService = patientService;
        this.missingToothRepository = missingToothRepository;
        this.missingToothMapper = missingToothMapper;
        this.teethService = teethService;

        this.patientMapper = patientMapper;
    }

    @Override
    public MissingToothDto addMissingToothByPatientId(String patientId, String teethId) {

        Teeth tooth = teethService.getTeethById(teethId);

        Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(Long.parseLong(patientId)), new CycleAvoidingMappingContext());

        MissingTooth missingTooth = new MissingTooth();

        missingTooth.setTeeth(tooth);

        patient.addMissingTooth(missingTooth);

        patientService.savePatient(patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext()));


        return missingToothMapper.missingToothToMissingToothDto(missingTooth, new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteMissingTooth(String toothId) {
        missingToothRepository.deleteById(Long.parseLong(toothId));
    }
}
