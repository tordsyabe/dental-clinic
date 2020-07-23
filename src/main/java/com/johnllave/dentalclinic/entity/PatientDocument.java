package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class PatientDocument extends BaseEntity {

    private String fileName;

    private String link;

    private LocalDate dateUploaded;

    private String fileType;

    @ManyToOne
    private Patient patient;
}
