package com.johnllave.dentalclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

    private String id;

    private String invoiceNo;

    private String datePaid;

    private String cost;

    private String dateCreated;


}
