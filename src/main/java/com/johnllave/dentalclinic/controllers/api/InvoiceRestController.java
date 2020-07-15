package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoiceRestController {

    private final InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceDto> getInvoices() {

        return invoiceService.getInvoices();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDto) {

        return invoiceService.saveInvoiceByProcedureId(invoiceDto.getProcedureId(), invoiceDto);
    }
}
