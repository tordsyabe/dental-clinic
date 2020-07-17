package com.johnllave.dentalclinic.request.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProcedureRequestModel {

    private String patientId;

    private String toothId;
}
