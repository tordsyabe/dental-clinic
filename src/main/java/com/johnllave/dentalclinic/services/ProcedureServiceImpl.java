package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Invoice;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.entity.Tooth;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.InvoiceMapper;
import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ToothService toothService;
    private final PatientService patientService;
    private final ProcedureMapper procedureMapper;
    private final PatientMapper patientMapper;
    private final ProcedureRepository procedureRepository;
    private final InvoiceMapper invoiceMapper;

    public ProcedureServiceImpl(ToothService toothService, PatientService patientService, ProcedureMapper procedureMapper, PatientMapper patientMapper, ProcedureRepository procedureRepository, InvoiceMapper invoiceMapper) {
        this.toothService = toothService;
        this.patientService = patientService;
        this.procedureMapper = procedureMapper;
        this.patientMapper = patientMapper;
        this.procedureRepository = procedureRepository;
        this.invoiceMapper = invoiceMapper;
    }


    @Override
    public List<ProcedureDto> getProcedures() {

        List<ProcedureDto> procedures = new ArrayList<>();

        procedureRepository.findAll().forEach(procedure -> procedures.add(procedureMapper.procedureToProcedureDto(procedure, new CycleAvoidingMappingContext())));

        return procedures;
    }

    @Override
    public ProcedureDto getProcedureById(String id) {

        Procedure procedure = procedureRepository.findByUuid(id);

        return procedureMapper.procedureToProcedureDto(procedure, new CycleAvoidingMappingContext());
    }

    @Override
    public List<ProcedureDto> getProceduresByDate(String date) {

        List<ProcedureDto> proceduresByDate = new ArrayList<>();

        procedureRepository.findAllByDateCreated(LocalDate.parse(date))
                .forEach(procedure -> proceduresByDate.add(procedureMapper.procedureToProcedureDto(procedure, new CycleAvoidingMappingContext())));


        return proceduresByDate;
    }

    @Override
    public ProcedureDto saveProcedureByPatientId(String patientId, ProcedureDto procedureDto) {
        Tooth toothToSave = toothService.getToothById(procedureDto.getTeethId());

        Patient patient = patientMapper.patientDtoToPatient(patientService.getPatientById(patientId), new CycleAvoidingMappingContext());

        procedureDto.setPaid("unpaid");

        Procedure procedure = procedureMapper.procedureDtoToProcedure(procedureDto, new CycleAvoidingMappingContext());

        procedure.setTooth(toothToSave);

        procedure.setPatient(patient);

        procedure.setUuid(UUID.randomUUID().toString());

        procedureRepository.save(procedure);

        return procedureMapper.procedureToProcedureDto(procedureRepository.save(procedure), new CycleAvoidingMappingContext());
    }


    @Override
    public List<ProcedureDto> getProceduresByPatientIdAndToothId(String patientId, String toothId) {

        PatientDto patientDto = patientService.getPatientById(patientId);

        List<ProcedureDto> procedures = new ArrayList<>();

        procedureRepository
                .findAllByPatientIdAndToothId(Long.parseLong(patientDto.getId()), Long.parseLong(toothId))
                .forEach(procedure -> procedures
                        .add(procedureMapper.procedureToProcedureDto(procedure, new CycleAvoidingMappingContext())));


        return procedures;
    }

    @Override
    public void deleteProcedure(String id) {

        Procedure procedure = procedureRepository.findByUuid(id);

        procedureRepository.delete(procedure);
    }

}
