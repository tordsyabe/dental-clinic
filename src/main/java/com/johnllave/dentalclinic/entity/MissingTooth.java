package com.johnllave.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "missing_tooth")
public class MissingTooth extends BaseEntity {

    @OneToOne
    private Tooth tooth;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
