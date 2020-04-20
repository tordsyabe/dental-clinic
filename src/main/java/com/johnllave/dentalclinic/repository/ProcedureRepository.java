package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

}
