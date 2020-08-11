package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.entity.Invoice;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.InvoiceMapper;
import com.johnllave.dentalclinic.mapper.ProcedureMapper;
import com.johnllave.dentalclinic.repository.InvoiceRepository;
import com.johnllave.dentalclinic.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final ProcedureService procedureService;
    private final ProcedureRepository procedureRepository;
    private final InvoiceMapper invoiceMapper;
    private final ProcedureMapper procedureMapper;
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(ProcedureService procedureService, ProcedureRepository procedureRepository, InvoiceMapper invoiceMapper, ProcedureMapper procedureMapper, InvoiceRepository invoiceRepository) {
        this.procedureService = procedureService;
        this.procedureRepository = procedureRepository;
        this.invoiceMapper = invoiceMapper;
        this.procedureMapper = procedureMapper;
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public InvoiceDto saveInvoiceByProcedureId(String id, InvoiceDto invoiceDto) {

        Procedure procedure = procedureMapper.procedureDtoToProcedure(procedureService.getProcedureById(id), new CycleAvoidingMappingContext());

        Invoice invoice = invoiceMapper.invoiceDtoToInvoice(invoiceDto, new CycleAvoidingMappingContext());

        invoice.setUuid(UUID.randomUUID().toString());

        invoice.setInvoiceNo(
                LocalDate.now().toString()
                + "-00" +
                procedure.getId().toString()
        );

        invoice.setDateCreated(LocalDate.now());

        if(invoice.getPaymentType().equals("partial")) {
            procedure.setPaid("partial");
        }

        if(invoice.getPaymentType().equals("discounted")) {
            procedure.setPaid("discounted");
        }

        if(invoice.getPaymentType().equals("full")) {
            procedure.setPaid("full");
        }

        invoice.setProcedure(procedure);

        procedureRepository.save(procedure);

        return invoiceMapper.invoiceToInvoiceDto(invoiceRepository.save(invoice), new CycleAvoidingMappingContext());
    }

    @Override
    public List<InvoiceDto> getInvoices() {
        List<InvoiceDto> invoices = new ArrayList<>();

        invoiceRepository.findAll().forEach(invoice -> invoices.add(invoiceMapper.invoiceToInvoiceDto(invoice, new CycleAvoidingMappingContext())));

        return invoices;
    }
}
