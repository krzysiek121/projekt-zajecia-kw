package pl.kurs.java.projektzajeciakw.mappings;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kurs.java.projektzajeciakw.model.Invoice;
import pl.kurs.java.projektzajeciakw.model.dto.InvoiceDto;
import pl.kurs.java.projektzajeciakw.request.CreateInvoiceCommand;
import pl.kurs.java.projektzajeciakw.service.InvoiceService;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class InvoiceToInvoiceDtoConverter implements Converter<Invoice, InvoiceDto> {

    private final InvoiceService invoiceService;


    @Override
    public InvoiceDto convert(MappingContext<Invoice, InvoiceDto> mappingContext) {
        Invoice invoice = mappingContext.getSource();
        return InvoiceDto.builder()
                .amountWithoutTax(invoiceService.count(invoice.getCountry(),invoice.getAmount()))
                .registrationDate(LocalDate.now())
                .country(invoice.getCountry())
                .build();
    }
}
