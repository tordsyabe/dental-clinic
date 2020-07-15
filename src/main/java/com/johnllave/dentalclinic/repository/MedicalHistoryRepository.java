package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

    MedicalHistory findByUuid(String id);
}
