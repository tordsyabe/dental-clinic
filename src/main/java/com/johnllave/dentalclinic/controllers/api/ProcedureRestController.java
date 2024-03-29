package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.request.model.ProcedureRequestModel;
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

    @GetMapping("/{dateCreated}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProcedureDto> getProceduresByDate(@PathVariable String dateCreated){

        return procedureService.getProceduresByDate(dateCreated);

    }

    @PostMapping("/tooth")
    @ResponseStatus(HttpStatus.OK)
    public List<ProcedureDto> getProceduresByPatientIdAndToothId(@RequestBody ProcedureRequestModel procedureRequestModel) {

        return procedureService.getProceduresByPatientIdAndToothId(procedureRequestModel.getPatientId(), procedureRequestModel.getToothId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProcedure(@PathVariable String id) {
        procedureService.deleteProcedure(id);
    }

}
