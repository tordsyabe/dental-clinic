package com.johnllave.dentalclinic.controllers.api;


import com.johnllave.dentalclinic.dto.AllergyDto;
import com.johnllave.dentalclinic.services.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/allergies")
public class AllergyRestController {

    private final AllergyService allergyService;

    @Autowired
    public AllergyRestController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AllergyDto saveAllergy(@RequestBody AllergyDto allergyDto) {

        return allergyService.saveAllergyByPatientId(allergyDto.getPatientId(), allergyDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllergy(@PathVariable String id) {

        allergyService.deleteAllergyById(id);
    }
}
