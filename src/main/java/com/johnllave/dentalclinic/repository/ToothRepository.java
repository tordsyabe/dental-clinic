package com.johnllave.dentalclinic.repository;

import java.util.Optional;

import com.johnllave.dentalclinic.entity.Tooth;
import org.springframework.data.repository.CrudRepository;

public interface ToothRepository extends CrudRepository<Tooth, Long>{
	Optional<Tooth> findByDescription(String description);
}
