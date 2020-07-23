package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.services.AWSS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    private final AWSS3Service awss3Service;

    @Autowired
    public FileController(AWSS3Service awss3Service) {
        this.awss3Service = awss3Service;
    }


    @PostMapping("uploadProfileImage")
    public String uploadFile(@RequestPart(value = "file") MultipartFile multipartFile,
                             @RequestParam(value = "patientId") String patientId) {

        awss3Service.uploadFile(multipartFile, patientId);

        return "redirect:/patient/details/" + patientId;
    }

    @PostMapping("uploadFile")
    public String uploadDocumentFile(@RequestPart(value = "file") MultipartFile multipartFile,
                                     @RequestParam(value = "patientId") String patientId,
                                     @RequestParam(value = "filename") String filename) {

        awss3Service.uploadFile(multipartFile, patientId, filename);

        return "redirect:/patient/documents/" + patientId;
    }
}
