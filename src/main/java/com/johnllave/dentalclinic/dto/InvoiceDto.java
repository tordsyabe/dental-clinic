package com.johnllave.dentalclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

    String id;

    String invoiceNo;

    String datePaid;

    String cost;

}
