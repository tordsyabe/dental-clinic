package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.DentalProcedureDto;
import com.johnllave.dentalclinic.request.model.DentalProcedureRequestModel;
import com.johnllave.dentalclinic.services.DentalProcedureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dental-procedures")
public class DentalProcedureRestController {

    private final DentalProcedureService dentalProcedureService;

    public DentalProcedureRestController(DentalProcedureService dentalProcedureService) {
        this.dentalProcedureService = dentalProcedureService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DentalProcedureDto saveDentalProcedure(@RequestBody DentalProcedureDto dentalProcedureDto) {

        return dentalProcedureService.saveDentalProcedure(dentalProcedureDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDentalProcedure(@PathVariable String id){

        dentalProcedureService.deleteDentalProcedure(id);
    }
}
