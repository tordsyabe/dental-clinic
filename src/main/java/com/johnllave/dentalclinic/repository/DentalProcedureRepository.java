package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.DentalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentalProcedureRepository extends JpaRepository<DentalProcedure, Long> {

    DentalProcedure findByUuid(String id);
}
