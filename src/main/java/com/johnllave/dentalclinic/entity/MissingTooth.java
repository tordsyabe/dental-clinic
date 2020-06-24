package com.johnllave.dentalclinic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "missing_tooth")
public class MissingTooth extends BaseEntity {

    @OneToOne
    private Teeth teeth;

    @ManyToOne
    private Patient patient;
}
