package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.MissingTooth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingToothRepository extends JpaRepository<MissingTooth, Long> {

    MissingTooth findByUuid(String id);
}
