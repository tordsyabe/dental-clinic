package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintDto extends BaseDto {

    private String description;
    private String dateCreated;
    private String patientId;

    @JsonIgnoreProperties("complaintsDto")
    private PatientDto patientDto;
}
