package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "dental_procedures")
public class DentalProcedure extends BaseEntity {

    private String description;

    private Integer cost;

    @ManyToOne
    private DentalProcedureCategory dentalProcedureCategory;

}
