package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "allergies")
public class Allergy extends BaseEntity {

    private String description;

    @ManyToOne
    private Patient patient;

    public Allergy() {
    }

    public Allergy(String description) {
        this.description = description;
    }
}
