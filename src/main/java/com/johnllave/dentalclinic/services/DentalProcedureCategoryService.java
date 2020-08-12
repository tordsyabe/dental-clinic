package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.DentalProcedureCategoryDto;

import java.util.List;

public interface DentalProcedureCategoryService {

    List<DentalProcedureCategoryDto> getCategories();

    DentalProcedureCategoryDto getCategory(String id);

    Long getCategoriesCount();
}
