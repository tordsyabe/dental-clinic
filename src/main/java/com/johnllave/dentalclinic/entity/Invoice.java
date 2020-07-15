package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    private String invoiceNo;

    private LocalDate datePaid;

    private Integer cost;

    private LocalDate dateCreated;

    @OneToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;


}
