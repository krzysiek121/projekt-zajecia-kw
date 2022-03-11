package pl.kurs.java.projektzajeciakw.controller;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
import pl.kurs.java.projektzajeciakw.model.Invoice;
import pl.kurs.java.projektzajeciakw.model.dto.InvoiceDto;
import pl.kurs.java.projektzajeciakw.request.CreateInvoiceCommand;
import pl.kurs.java.projektzajeciakw.response.AllTaxResponse;
import pl.kurs.java.projektzajeciakw.service.InvoiceService;

import javax.validation.Valid;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<InvoiceDto> saveInvoice(@RequestBody @Valid CreateInvoiceCommand command) {
        Invoice saved = invoiceService.save(command);
        return new ResponseEntity<>(modelMapper.map(saved, InvoiceDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        return ResponseEntity.ok(getListInvoiceDto());
    }

    @GetMapping("/taxSummary")
    public ResponseEntity<AllTaxResponse> getAllTax() {
        return ResponseEntity.ok(new AllTaxResponse(invoiceService.getAllTax(modelMapper),
                invoiceService.getSummaryTax(modelMapper)));
    }

    @GetMapping("/taxSummary/{month}")
    public ResponseEntity<Map<String, Double>> getAllTaxByMonth(@PathVariable(value = "month") String month) {
        return ResponseEntity.ok(invoiceService.getTaxByMonth(month, modelMapper));
    }

    public List<InvoiceDto> getListInvoiceDto() {

        return invoiceService.getInvoices()
                .stream()
                .map(s -> modelMapper.map(s, InvoiceDto.class))
                .collect(Collectors.toList());
    }


}

