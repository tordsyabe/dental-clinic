package com.johnllave.dentalclinic.request.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MissingToothRequestModel {

    private String toothId;

    private String patientId;
}
