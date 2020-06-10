package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.services.ProcedureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/procedures")
public class ProcedureRestController {

    private final ProcedureService procedureService;

    public ProcedureRestController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProcedureDto> getProcedures() {

        return procedureService.getProcedures();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProcedureDto createInvoice(@PathVariable String id, @RequestBody InvoiceDto invoiceDto) {

        return procedureService.createInvoiceByProcedureId(Long.parseLong(id), invoiceDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProcedure(@PathVariable String id) {
        procedureService.deleteProcedureById(id);
    }

}
