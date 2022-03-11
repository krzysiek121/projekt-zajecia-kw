package pl.kurs.java.projektzajeciakw.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
import pl.kurs.java.projektzajeciakw.model.Invoice;
import pl.kurs.java.projektzajeciakw.model.dto.InvoiceDto;
import pl.kurs.java.projektzajeciakw.repository.InvoiceRepository;
import pl.kurs.java.projektzajeciakw.request.CreateInvoiceCommand;
import pl.kurs.java.projektzajeciakw.response.AllTaxResponse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final Map<String, ICountry> operationsMap;
    private final InvoiceRepository invoiceRepository;

    // private final CreateInvoiceCommand createInvoiceCommand;

    public InvoiceService(Set<ICountry> operations, InvoiceRepository invoiceRepository) {
        this.operationsMap = operations.stream().collect(Collectors.toMap(ICountry::getName, Function.identity()));
        this.invoiceRepository = invoiceRepository;


    }


    public double count(String country, Double liczba) {
        double result = operationsMap.get(country).calculate(liczba);

        return result;
    }

    public List<Invoice> getInvoices() {

        return invoiceRepository.findAll();
    }

    public Invoice save(CreateInvoiceCommand createCommand) {
        Invoice toSave = new Invoice(createCommand.getCountry(), createCommand.getAmount(), LocalDate.now());
        return invoiceRepository.saveAndFlush(toSave);
    }

    public Map<String, Double> getAllTax(ModelMapper model) {
        Map<String, Double> map = new ConcurrentHashMap<>();

        for (InvoiceDto a : getListInvoiceDto(model)) {
            if (!map.containsKey(a.getCountry())) {
                map.put(a.getCountry(), a.getAmountWithoutTax());
            } else {
                map.put(a.getCountry(), map.get(a.getCountry()) + a.getAmountWithoutTax());
            }
        }


        return map;
    }

    public List<InvoiceDto> getListInvoiceDto(ModelMapper modelMapper) {

        return getInvoices()
                .stream()
                .map(s -> modelMapper.map(s, InvoiceDto.class))
                .collect(Collectors.toList());
    }

    public Double getSummaryTax(ModelMapper modelMapper) {
        return getListInvoiceDto(modelMapper).stream().mapToDouble(s -> s.getAmountWithoutTax()).sum();

    }

    public Map<String, Double> getTaxByMonth(String month, ModelMapper modelMapper) {
        Map<String, Double> map = new ConcurrentHashMap<>();

        for (InvoiceDto a : getListInvoiceDtoByMonth(month, modelMapper)) {
            if (!map.containsKey(a.getCountry())) {
                map.put(a.getCountry(), a.getAmountWithoutTax());
            } else {
                map.put(a.getCountry(), map.get(a.getCountry()) + a.getAmountWithoutTax());
            }
        }
        return map;
    }

    public List<InvoiceDto> getListInvoiceDtoByMonth(String month, ModelMapper modelMapper) {

        return getInvoices()
                .stream()
                .filter(s -> s.getRegistrationDate().getMonth().equals(Month.valueOf(month.toUpperCase(Locale.ROOT))))
                .map(s -> modelMapper.map(s, InvoiceDto.class))
                .collect(Collectors.toList());
    }

}

