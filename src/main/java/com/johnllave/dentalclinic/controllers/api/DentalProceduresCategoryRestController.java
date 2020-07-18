package com.johnllave.dentalclinic.controllers.api;

import com.johnllave.dentalclinic.dto.DentalProcedureCategoryDto;
import com.johnllave.dentalclinic.entity.DentalProcedureCategory;
import com.johnllave.dentalclinic.services.DentalProcedureCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dental-procedure-categories")
public class DentalProceduresCategoryRestController {

    private final DentalProcedureCategoryService dentalProcedureCategoryService;

    public DentalProceduresCategoryRestController(DentalProcedureCategoryService dentalProcedureCategoryService) {
        this.dentalProcedureCategoryService = dentalProcedureCategoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DentalProcedureCategoryDto> getDentalProcedureCategories() {

        return dentalProcedureCategoryService.getCategories();
    }
}
