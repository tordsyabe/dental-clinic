package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.MedicalHistoryDto;
import com.johnllave.dentalclinic.services.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/medicalhistories")
public class MedicalHsitoryRestController {

    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHsitoryRestController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MedicalHistoryDto saveMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto) {

        return medicalHistoryService.saveMedicalHistoryByPatientId(medicalHistoryDto.getPatientId(), medicalHistoryDto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicalHistory(@PathVariable String id) {

        medicalHistoryService.deleteMedicalHistoryById(id);
    }
}
