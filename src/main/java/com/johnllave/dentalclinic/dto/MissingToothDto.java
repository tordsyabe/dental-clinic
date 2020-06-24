package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MissingToothDto {

    private String teethId;
    private String patientId;
}
