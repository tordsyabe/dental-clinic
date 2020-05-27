package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AllergyDto {

    private String id;

    private String description;

    private String dateCreated;

    private String patientId;

    private PatientDto patientDto;

}
