package com.johnllave.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

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
