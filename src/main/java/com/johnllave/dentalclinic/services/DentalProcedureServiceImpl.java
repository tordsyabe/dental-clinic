package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.DentalProcedureCategoryDto;
import com.johnllave.dentalclinic.dto.DentalProcedureDto;
import com.johnllave.dentalclinic.entity.DentalProcedure;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.DentalProcedureMapper;
import com.johnllave.dentalclinic.repository.DentalProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DentalProcedureServiceImpl implements DentalProcedureService {

    private final DentalProcedureRepository dentalProcedureRepository;
    private final DentalProcedureMapper dentalProcedureMapper;
    private final DentalProcedureCategoryService dentalProcedureCategoryService;

    public DentalProcedureServiceImpl(DentalProcedureRepository dentalProcedureRepository, DentalProcedureMapper dentalProcedureMapper, DentalProcedureCategoryService dentalProcedureCategoryService) {
        this.dentalProcedureRepository = dentalProcedureRepository;
        this.dentalProcedureMapper = dentalProcedureMapper;
        this.dentalProcedureCategoryService = dentalProcedureCategoryService;
    }

    @Override
    public DentalProcedureDto saveDentalProcedure(DentalProcedureDto dentalProcedureDto) {

        DentalProcedureCategoryDto categoryDto = dentalProcedureCategoryService
                .getCategory(dentalProcedureDto.getCategoryId());

        dentalProcedureDto.setDentalProcedureCategoryDto(categoryDto);


        if(!dentalProcedureDto.getUuid().isEmpty()) {

            DentalProcedure dentalProcedureFromDb = dentalProcedureRepository.findByUuid(dentalProcedureDto.getUuid());
            dentalProcedureDto.setId(dentalProcedureFromDb.getId().toString());

            DentalProcedure procedureDto = dentalProcedureRepository.save(dentalProcedureMapper.dtoToEntity(dentalProcedureDto,
                    new CycleAvoidingMappingContext()));

            return dentalProcedureMapper.entityToDto(procedureDto, new CycleAvoidingMappingContext());
        }

        dentalProcedureDto.setUuid(UUID.randomUUID().toString());

        DentalProcedure procedureDto = dentalProcedureRepository.save(dentalProcedureMapper.dtoToEntity(dentalProcedureDto,
                new CycleAvoidingMappingContext()));

        return dentalProcedureMapper.entityToDto(procedureDto, new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteDentalProcedure(String id) {

        DentalProcedure dentalProcedure = dentalProcedureRepository.findByUuid(id);

        dentalProcedureRepository.delete(dentalProcedure);
    }
}
