package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    Allergy findByUuid(String id);
}
