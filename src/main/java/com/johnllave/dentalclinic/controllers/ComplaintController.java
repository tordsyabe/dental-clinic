package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.ComplaintDto;
import com.johnllave.dentalclinic.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComplaintDto saveComplaint(@RequestBody ComplaintDto complaintDto) {

        return complaintService.saveComplaintByPatientId(complaintDto.getPatientId(), complaintDto);
    }
}
