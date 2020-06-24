package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.services.MissingToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/missingtooth")
public class MissingToothController {

    private final MissingToothService missingToothService;

    @Autowired
    public MissingToothController(MissingToothService missingToothService) {
        this.missingToothService = missingToothService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MissingToothDto saveMissingTooth(@RequestBody MissingToothDto missingToothDto) {
        MissingToothDto savedMissingTooth = missingToothService.addMissingToothByPatientId(missingToothDto.getPatientId(), missingToothDto.getTeethId());

        return savedMissingTooth;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMissingTooth(@PathVariable String id) {

        missingToothService.deleteMissingTooth(id);
    }
}
