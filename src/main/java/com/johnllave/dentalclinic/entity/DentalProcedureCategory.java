package com.johnllave.dentalclinic.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class DentalProcedureCategory extends BaseEntity{

    @NotBlank
    private String name;

    private String icon;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dentalProcedureCategory")
    private List<DentalProcedure> dentalProcedures = new ArrayList<>();

    public DentalProcedureCategory addDentalProcedure(DentalProcedure dentalProcedure) {
        dentalProcedure.setDentalProcedureCategory(this);
        this.dentalProcedures.add(dentalProcedure);
        return this;
    }
}
