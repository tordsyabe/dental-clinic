package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
