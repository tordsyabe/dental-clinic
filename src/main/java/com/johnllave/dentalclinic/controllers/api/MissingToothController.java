package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.MissingToothDto;
import com.johnllave.dentalclinic.request.model.MissingToothRequestModel;
import com.johnllave.dentalclinic.services.MissingToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/missing-tooth")
public class MissingToothController {

    private final MissingToothService missingToothService;

    @Autowired
    public MissingToothController(MissingToothService missingToothService) {
        this.missingToothService = missingToothService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MissingToothDto saveMissingTooth(@RequestBody MissingToothRequestModel missingToothRequestModel) {

        return missingToothService.addMissingToothByPatientId(missingToothRequestModel.getPatientId(),
                missingToothRequestModel.getToothId());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMissingTooth(@PathVariable String id) {

        missingToothService.deleteMissingTooth(id);

    }
}
