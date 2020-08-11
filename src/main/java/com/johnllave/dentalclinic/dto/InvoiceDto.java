package com.johnllave.dentalclinic.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto extends BaseDto{


    private String invoiceNo;

    private String datePaid;

    private String cost;

    private String paymentType;

    private String dateCreated;

    private String procedureId;

    @JsonIgnoreProperties("invoicesDto")
    private ProcedureDto procedureDto;
}
