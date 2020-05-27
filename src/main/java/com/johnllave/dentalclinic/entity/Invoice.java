package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    private String invoiceNo;

    private LocalDate datePaid;

    private Integer cost;

    private LocalDate dateCreated;


    public Invoice() {
    }

    public Invoice(String invoiceNo, LocalDate datePaid, Integer cost, LocalDate dateCreated) {
        this.invoiceNo = invoiceNo;
        this.datePaid = datePaid;
        this.cost = cost;
        this.dateCreated = dateCreated;
    }
}
