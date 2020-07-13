package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MissingToothDto {

    private String id;

    private String teethId;
    private String patientId;

    private TeethDto teeth;

    @JsonIgnoreProperties("missingTeethDto")
    private PatientDto patientDto;
}
