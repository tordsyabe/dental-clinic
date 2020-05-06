package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.services.PatientService;
import com.johnllave.dentalclinic.services.ProcedureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public ProcedureController(ProcedureService procedureService, PatientService patientService, PatientMapper patientMapper) {
        this.procedureService = procedureService;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping("patient/procedures/{patientId}")
    public String saveProcedure(@PathVariable String patientId, @Valid @ModelAttribute ProcedureDto procedureDto,
                                BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            Patient patient = patientService.getPatientById(Long.parseLong(patientId));

            model.addAttribute("patient", patientMapper.patientToPatientDto(patient));
            return "patient/procedures";
        }

        PatientDto savedPatientProcedure = procedureService.saveProcedureByPatientId(patientId, procedureDto);

        return "redirect:/patient/details/" + savedPatientProcedure.getId();
    }


}
