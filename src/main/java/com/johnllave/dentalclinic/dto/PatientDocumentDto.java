package com.johnllave.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientDocumentDto extends BaseDto {

    private String fileName;

    private String link;

    private String dateUploaded;

    private String fileType;

    @JsonIgnoreProperties("patientDocumentsDto")
    private PatientDto patientDto;

}
