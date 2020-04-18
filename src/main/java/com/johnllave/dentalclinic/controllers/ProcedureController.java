package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.services.ProcedureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @PostMapping("patient/procedures/{patientId}")
    public String saveVisit(@PathVariable String patientId, @ModelAttribute ProcedureDto procedureDto) {

        PatientDto savedPatientProcedure = procedureService.saveProcedureByPatientId(patientId, procedureDto);

        return "redirect:/patient/details/" + savedPatientProcedure.getId();
    }
}
