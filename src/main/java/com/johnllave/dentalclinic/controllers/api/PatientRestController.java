package com.johnllave.dentalclinic.controllers.api;

import com.amazonaws.services.dynamodbv2.xspec.L;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    private final PatientService patientService;

    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDto> getPatients() {

        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto getPatient(@PathVariable String id) {
//        System.out.println(patientService.getPatientById(Long.parseLong(id)));
        return patientService.getPatientById(id);
    }
}
