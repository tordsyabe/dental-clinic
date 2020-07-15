package com.johnllave.dentalclinic.repository;

import com.johnllave.dentalclinic.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
