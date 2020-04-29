package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Complaint extends BaseEntity {

    private String description;
    private LocalDate date;

    @ManyToOne
    Patient patient;
}
