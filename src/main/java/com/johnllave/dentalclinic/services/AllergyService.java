package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.entity.Allergy;

public interface AllergyService {

    AllergyDto saveAllergy(AllergyDto allergyDto);

    void deleteAllergy(String id);
}
