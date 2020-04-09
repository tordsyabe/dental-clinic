package com.johnllave.dentalclinic.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.johnllave.dentalclinic.entity.Teeth;

public interface TeethRepository extends CrudRepository<Teeth, Long>{
	Optional<Teeth> findByDescription(String description);
}
