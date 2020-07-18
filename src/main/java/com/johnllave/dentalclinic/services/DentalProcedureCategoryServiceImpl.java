package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.DentalProcedureCategoryDto;
import com.johnllave.dentalclinic.entity.DentalProcedureCategory;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.DentalProcedureCategoryMapper;
import com.johnllave.dentalclinic.repository.DentalProcedureCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentalProcedureCategoryServiceImpl implements DentalProcedureCategoryService {

    private final DentalProcedureCategoryRepository dentalCategoryRepo;
    private final DentalProcedureCategoryMapper dentalCategoryMapper;

    public DentalProcedureCategoryServiceImpl(DentalProcedureCategoryRepository dentalCategoryRepo, DentalProcedureCategoryMapper dentalCategoryMapper) {
        this.dentalCategoryRepo = dentalCategoryRepo;
        this.dentalCategoryMapper = dentalCategoryMapper;
    }

    @Override
    public List<DentalProcedureCategoryDto> getCategories() {

        List<DentalProcedureCategoryDto> dentalProcedureCategoriesDto = new ArrayList<>();

        dentalCategoryRepo.findAll().forEach(dentalProcedureCategory -> dentalProcedureCategoriesDto.add(
            dentalCategoryMapper.entityToDto(dentalProcedureCategory, new CycleAvoidingMappingContext())
        ));

        return dentalProcedureCategoriesDto;
    }
}
