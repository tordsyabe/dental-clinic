package com.johnllave.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "medical_histories")
public class MedicalHistory extends BaseEntity {

    private String description;

    private LocalDate dateCreated;

    @ManyToOne
    private Patient patient;

    public MedicalHistory() {
    }

    public MedicalHistory(String description) {
        this.description = description;
    }
}
