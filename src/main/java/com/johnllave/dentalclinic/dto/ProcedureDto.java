package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProcedureDto {

    private String id;

    @NotBlank
    private String dateCreated;

    @NotBlank
    private String description;

    @NotBlank
    private String cost;

    private Boolean paid;

    @NotBlank
    private String category;

    @NotBlank
    private String teethId;

    private PatientDto patientDto;


}
