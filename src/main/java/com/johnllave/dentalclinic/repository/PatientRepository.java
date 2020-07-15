package com.johnllave.dentalclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.johnllave.dentalclinic.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long>{
    Patient findByUuid(String id);
}
