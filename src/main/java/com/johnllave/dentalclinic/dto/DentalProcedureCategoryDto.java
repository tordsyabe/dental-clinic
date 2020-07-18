package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DentalProcedureCategoryDto extends BaseDto{

    private String name;

    @JsonIgnoreProperties("dentalProcedureCategoryDto")
    private List<DentalProcedureDto> dentalProceduresDto  = new ArrayList<>();
}
