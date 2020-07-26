package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnllave.dentalclinic.entity.DentalProcedureCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DentalProcedureDto extends BaseDto {

    private String description;

    private String cost;

    private String categoryId;

    @JsonIgnoreProperties("dentalProceduresDto")
    private DentalProcedureCategoryDto dentalProcedureCategoryDto;
}
