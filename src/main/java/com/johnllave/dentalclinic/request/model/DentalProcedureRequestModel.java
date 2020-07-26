package com.johnllave.dentalclinic.request.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DentalProcedureRequestModel {

    private String categoryId;

    private String procedureId;

    private String description;

    private String cost;
}
