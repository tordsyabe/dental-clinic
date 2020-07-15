package com.johnllave.dentalclinic.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "allergies")
public class Allergy extends BaseEntity {

    private String description;

    private LocalDate dateCreated;

    @ManyToOne
    private Patient patient;

    public Allergy() {
    }

    public Allergy(String description) {
        this.description = description;
    }
}
