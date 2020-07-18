package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.DentalProcedureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentalProcedureCategoryRepository extends JpaRepository<DentalProcedureCategory, Long> {
}
