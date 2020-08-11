package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcedureDto extends BaseDto {

    @NotBlank
    private String dateCreated;

    @NotBlank
    private String description;

    @NotBlank
    private String cost;

    private String paid;

    @NotBlank
    private String category;

    @NotBlank
    private String teethId;

    private String patientId;

    private ToothDto toothDto;

    @JsonIgnoreProperties("proceduresDto")
    private List<InvoiceDto> invoicesDto = new ArrayList<>();

    @JsonIgnoreProperties("proceduresDto")
    private PatientDto patientDto;


}
