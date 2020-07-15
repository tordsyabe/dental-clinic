package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {

    InvoiceDto saveInvoiceByProcedureId(String id, InvoiceDto invoiceDto);

    List<InvoiceDto> getInvoices();
}
