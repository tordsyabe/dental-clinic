package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<Procedure> findAllByDateCreated(LocalDate dateCreated);

    Procedure findByUuid(String id);

    List<Procedure> findAllByPatientIdAndToothId(String patientId, String toothId);
}
