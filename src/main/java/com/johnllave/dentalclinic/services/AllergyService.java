package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.AllergyDto;

public interface AllergyService {

    AllergyDto saveAllergyByPatientId(String id, AllergyDto allergyDto);

    void deleteAllergyById(String id);
}
