package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Invoice;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<ProcedureDto> getProcedures() {

        List<ProcedureDto> procedures = new ArrayList<>();

        procedureRepository.findAll().forEach(procedure -> procedures.add(procedureMapper.procedureToProcedureDto(procedure, new CycleAvoidingMappingContext())));

        return procedures;
    }

    @Override
    public List<ProcedureDto> getProceduresByDate(String date) {

        List<ProcedureDto> proceduresByDate = new ArrayList<>();

        procedureRepository.findAllByDateCreated(LocalDate.parse(date))
                .forEach(procedure -> proceduresByDate.add(procedureMapper.procedureToProcedureDto(procedure, new CycleAvoidingMappingContext())));


        return proceduresByDate;
    }

    @Override
    public PatientDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto) {
        Teeth teethToSave = teethService.getTeethById(procedureDto.getTeethId());

        Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(Long.parseLong(patientId)), new CycleAvoidingMappingContext());

        procedureDto.setPaid(false);

        Procedure procedure = procedureMapper.procedureDtoToProcedure(procedureDto, new CycleAvoidingMappingContext());

        procedure.setTeeth(teethToSave);

        patient.addProcedure(procedure);

        PatientDto patientDto = patientMapper.patientToPatientDto(patient, new CycleAvoidingMappingContext());

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

        return procedureMapper.procedureToProcedureDto(savedProcedure, new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteProcedureById(String id) {
        procedureRepository.deleteById(Long.parseLong(id));
    }

}
