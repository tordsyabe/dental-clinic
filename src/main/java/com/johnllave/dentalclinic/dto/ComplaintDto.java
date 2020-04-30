package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintDto {

    private String id;
    private String description;
    private String date;
    private String patientId;

    private PatientDto patientDto;
}
