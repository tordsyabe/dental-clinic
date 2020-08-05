package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {

    PatientDocument findByUuid(String id);
}
