package com.johnllave.dentalclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VisitDto {

    private String id;
    private PatientDto patientDto;
    private String date;

    private Set<ProcedureDto> proceduresDto = new HashSet<>();

}
