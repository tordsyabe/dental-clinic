package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Allergy;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.AllergyMapper;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.repository.AllergyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AllergyServiceImpl implements AllergyService {

    private final AllergyRepository allergyRepository;
    private final AllergyMapper allergyMapper;
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public AllergyServiceImpl(AllergyRepository allergyRepository, AllergyMapper allergyMapper, PatientService patientService, PatientMapper patientMapper) {
        this.allergyRepository = allergyRepository;
        this.allergyMapper = allergyMapper;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @Override
    public AllergyDto saveAllergy(AllergyDto allergyDto) {

        PatientDto patientDto = patientService.getPatientById(allergyDto.getPatientId());

        Patient patient = patientMapper.patientDtoToPatient(patientDto, new CycleAvoidingMappingContext());


        Allergy allergy = allergyMapper.allergyDtoToAllergy(allergyDto, new CycleAvoidingMappingContext());

        allergy.setPatient(patient);
        allergy.setUuid(UUID.randomUUID().toString());


        return allergyMapper.allergyToAllergyDto(allergyRepository.save(allergy), new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteAllergy(String id) {

        Allergy allergy = allergyRepository.findByUuid(id);

        allergyRepository.delete(allergy);
    }
}
