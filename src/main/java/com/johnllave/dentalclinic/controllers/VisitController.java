package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.dto.TeethDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.entity.Visit;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.mapper.TeethMapper;
import com.johnllave.dentalclinic.repository.VisitRepository;
import com.johnllave.dentalclinic.services.PatientService;
import com.johnllave.dentalclinic.services.TeethService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class VisitController {

    private final TeethService teethService;
    private final TeethMapper teethMapper;
    private final VisitRepository visitRepository;
    private final ProcedureMapper procedureMapper;
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public VisitController(TeethService teethService, TeethMapper teethMapper, VisitRepository visitRepository, ProcedureMapper procedureMapper, PatientService patientService, PatientMapper patientMapper) {
        this.teethService = teethService;
        this.teethMapper = teethMapper;
        this.visitRepository = visitRepository;
        this.procedureMapper = procedureMapper;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping("patient/procedures/{patientId}")
    public String saveVisit(@PathVariable String patientId, @ModelAttribute ProcedureDto procedureDto) {

        Teeth teethToSave = teethService.getTeethById(Long.parseLong(procedureDto.getTeethId()));

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));

        procedureDto.setCost("600");
        procedureDto.setPaid(false);

        Procedure convertedProcedure = procedureMapper.procedureDtoToProcedure(procedureDto);

        convertedProcedure.setTeeth(teethToSave);

        Visit visit = new Visit();

        visit.addProcedure(convertedProcedure);
        visit.setDate(LocalDate.now());
        patient.addVisit(visit);

        PatientDto patientDto = patientMapper.patientToPatientDto(patient);

        System.out.println(teethToSave.toString());
        System.out.println(convertedProcedure.toString());
        System.out.println(visit.toString());
        System.out.println(procedureDto.toString());

        patientService.savePatient(patientDto);

        return "redirect:/patient/details/" + patient.getId();
    }
}
