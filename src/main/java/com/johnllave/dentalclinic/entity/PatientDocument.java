package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class PatientDocument extends BaseEntity {

    private String fileName;

    private LocalDate dateUploaded;

    private Patient patient;
}
