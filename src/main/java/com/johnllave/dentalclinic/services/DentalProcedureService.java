package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.DentalProcedureDto;

public interface DentalProcedureService {

    DentalProcedureDto saveDentalProcedure(DentalProcedureDto dentalProcedureDto);

    void deleteDentalProcedure(String id);
}
