package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.services.AWSS3Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patient-documents")
public class PatientDocumentRestController {

    private final AWSS3Service awss3Service;

    public PatientDocumentRestController(AWSS3Service awss3Service) {
        this.awss3Service = awss3Service;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePatientDocument(@PathVariable String id) {

        awss3Service.deleteFileFromS3Bucket(id);
    }

}
