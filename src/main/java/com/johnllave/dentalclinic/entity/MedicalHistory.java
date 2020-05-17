package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "medical_histories")
public class MedicalHistory extends BaseEntity {

    private String description;

    @ManyToOne
    private Patient patient;

    public MedicalHistory() {
    }

    public MedicalHistory(String description) {
        this.description = description;
    }
}
