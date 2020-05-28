package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Invoice;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final TeethService teethService;
    private final PatientService patientService;
    private final ProcedureMapper procedureMapper;
    private final PatientMapper patientMapper;
    private final ProcedureRepository procedureRepository;

    public ProcedureServiceImpl(TeethService teethService, PatientService patientService, ProcedureMapper procedureMapper, PatientMapper patientMapper, ProcedureRepository procedureRepository) {
        this.teethService = teethService;
        this.patientService = patientService;
        this.procedureMapper = procedureMapper;

        this.patientMapper = patientMapper;
        this.procedureRepository = procedureRepository;
    }


    @Override
    public PatientDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto) {
        Teeth teethToSave = teethService.getTeethById(Long.parseLong(procedureDto.getTeethId()));

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));

        procedureDto.setPaid(false);

        Procedure procedure = procedureMapper.procedureDtoToProcedure(procedureDto);

        procedure.setTeeth(teethToSave);
        procedure.setDateCreated(LocalDate.now());

        patient.addProcedure(procedure);

        PatientDto patientDto = patientMapper.patientToPatientDto(patient);

        return patientService.savePatient(patientDto);
    }

    @Override
    public ProcedureDto createInvoiceByProcedureId(Long id, InvoiceDto invoiceDto) {

        Procedure procedure =  procedureRepository.findById(id).orElse(null);
        procedure.setPaid(true);
        procedure.setInvoice(new Invoice(LocalDate.now()
                .toString()
                .replace("-", "") + "-0" + procedure.getId(),
                LocalDate.parse(invoiceDto.getDatePaid()),
                Integer.parseInt(invoiceDto.getCost()),
                LocalDate.now()));

        Procedure savedProcedure = procedureRepository.save(procedure);

        return procedureMapper.procedureToProcedureDto(savedProcedure);
    }

    @Override
    public void deleteProcedureById(String id) {
        procedureRepository.deleteById(Long.parseLong(id));
    }

}
