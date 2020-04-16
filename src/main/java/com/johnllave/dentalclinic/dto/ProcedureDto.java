package com.johnllave.dentalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProcedureDto {

    private String id;
    private String description;
    private String cost;
    private Boolean paid;
    private String category;

    private String teethId;
//    private VisitDto visitDto;
}
