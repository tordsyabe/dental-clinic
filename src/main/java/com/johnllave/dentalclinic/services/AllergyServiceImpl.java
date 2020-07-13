package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.entity.Allergy;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.AllergyMapper;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.repository.AllergyRepository;
import org.springframework.stereotype.Service;

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
    public AllergyDto saveAllergyByPatientId(String id, AllergyDto allergyDto) {

        Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(Long.parseLong(id)), new CycleAvoidingMappingContext());

        Allergy allergy = allergyMapper.allergyDtoToAllergy(allergyDto, new CycleAvoidingMappingContext());

        allergy.setPatient(patient);

        return allergyMapper.allergyToAllergyDto(allergyRepository.save(allergy), new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteAllergyById(String id) {
        allergyRepository.deleteById(Long.parseLong(id));
    }
}
