package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.entity.Allergy;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.AllergyMapper;
import com.johnllave.dentalclinic.repository.AllergyRepository;
import org.springframework.stereotype.Service;

@Service
public class AllergyServiceImpl implements AllergyService {

    private final AllergyRepository allergyRepository;
    private final AllergyMapper allergyMapper;
    private final PatientService patientService;

    public AllergyServiceImpl(AllergyRepository allergyRepository, AllergyMapper allergyMapper, PatientService patientService) {
        this.allergyRepository = allergyRepository;
        this.allergyMapper = allergyMapper;
        this.patientService = patientService;
    }

    @Override
    public AllergyDto saveAllergyByPatientId(String id, AllergyDto allergyDto) {

        Patient patient = patientService.getPatientById(Long.parseLong(id));

        Allergy allergy = allergyMapper.allergyDtoToAllergy(allergyDto);

        allergy.setPatient(patient);

        return allergyMapper.allergyToAllergyDto(allergyRepository.save(allergy));
    }

    @Override
    public void deleteAllergyById(String id) {
        allergyRepository.deleteById(Long.parseLong(id));
    }
}
