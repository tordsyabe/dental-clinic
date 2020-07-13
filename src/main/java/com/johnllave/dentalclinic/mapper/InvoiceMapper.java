package com.johnllave.dentalclinic.mapper;

import com.johnllave.dentalclinic.dto.InvoiceDto;
import com.johnllave.dentalclinic.entity.Invoice;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ProcedureMapper.class, componentModel = "spring")
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(target = "id", source = "invoice.id")
    @Mapping(target = "dateCreated", source = "invoice.dateCreated", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "cost", source = "invoice.cost")
    @Mapping(target = "datePaid", source = "invoice.datePaid", dateFormat = "yyyy-MM-dd")
    InvoiceDto invoiceToInvoiceDto(Invoice invoice, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "id", source = "invoiceDto.id")
    @Mapping(target = "dateCreated", source = "invoiceDto.dateCreated", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "cost", source = "invoiceDto.cost")
    @Mapping(target = "datePaid", source = "invoiceDto.datePaid", dateFormat = "yyyy-MM-dd")
    Invoice invoiceDtoToInvoice(InvoiceDto invoiceDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
