package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.entity.MissingTooth;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Tooth;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.MissingToothMapper;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ToothMapper;
import com.johnllave.dentalclinic.repository.MissingToothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissingToothServiceImpl implements MissingToothService {


    private final PatientService patientService;
    private final MissingToothRepository missingToothRepository;
    private final MissingToothMapper missingToothMapper;
    private final ToothService toothService;
    private final PatientMapper patientMapper;
    private final ToothMapper toothMapper;


    @Autowired
    public MissingToothServiceImpl(PatientService patientService, MissingToothRepository missingToothRepository, MissingToothMapper missingToothMapper, ToothService toothService, PatientMapper patientMapper, ToothMapper toothMapper) {

        this.patientService = patientService;
        this.missingToothRepository = missingToothRepository;
        this.missingToothMapper = missingToothMapper;
        this.toothService = toothService;
        this.patientMapper = patientMapper;
        this.toothMapper = toothMapper;
    }

    @Override
    public MissingToothDto addMissingToothByPatientId(String patientId, String teethId) {

        Tooth tooth = toothService.getToothById(teethId);

        Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(patientId), new CycleAvoidingMappingContext());

        MissingTooth missingTooth = new MissingTooth();

        missingTooth.setTooth(tooth);

        patient.addMissingTooth(missingTooth);

        patientService.savePatient(patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext()));


        return missingToothMapper.missingToothToMissingToothDto(missingTooth, new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteMissingTooth(String toothId) {
        missingToothRepository.deleteById(Long.parseLong(toothId));
    }
}
