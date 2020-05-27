package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalHistoryDto {

    private String id;

    private String description;

    private String dateCreated;

    private String patientId;

}
