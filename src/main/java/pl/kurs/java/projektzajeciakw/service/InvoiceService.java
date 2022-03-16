package pl.kurs.java.projektzajeciakw.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
import pl.kurs.java.projektzajeciakw.model.Company;
import pl.kurs.java.projektzajeciakw.model.Invoice;
import pl.kurs.java.projektzajeciakw.model.dto.InvoiceDto;
import pl.kurs.java.projektzajeciakw.repository.CompanyRepository;
import pl.kurs.java.projektzajeciakw.repository.InvoiceRepository;
import pl.kurs.java.projektzajeciakw.request.CreateInvoiceCommand;
import pl.kurs.java.projektzajeciakw.validation.annotation.CheckName;

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
    private final CompanyRepository companyRepository;

    // private final CreateInvoiceCommand createInvoiceCommand;

    public InvoiceService(Set<ICountry> operations, InvoiceRepository invoiceRepository, CompanyRepository companyRepository) {
        this.operationsMap = operations.stream().collect(Collectors.toMap(ICountry::getName, Function.identity()));
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
    }

    public double count(String country, Double liczba) {
        double result = operationsMap.get(country).calculate(liczba);

        return result;
    }

    public List<Invoice> getInvoices() {

        return invoiceRepository.findAll();
    }

    public Invoice save(CreateInvoiceCommand createCommand) {
        Invoice toSave = new Invoice(createCommand.getCountry(), createCommand.getAmount(), LocalDate.now(), createCommand.getCompany());
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

    public Map<String, Double> getTaxByMonth(String name, String month, ModelMapper modelMapper) {
        Map<String, Double> map = new ConcurrentHashMap<>();

        for (InvoiceDto a : getCompaniesInvoiceByMonthAndName(name , month, modelMapper)) {
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

    @Transactional
    public void zabawa() {
        Company s1 = new Company("Karad");
        new Invoice("PL", 300, LocalDate.now(), s1);
        new Invoice("PL", 200, LocalDate.now(), s1);
        new Invoice("DE", 300, LocalDate.now(), s1);
        new Invoice("FR", 200, LocalDate.now(), s1);
        //new Invoice("napisz relacje do swojego projektu", LocalDate.now().plusDays(5), null, s1);

        companyRepository.saveAndFlush(s1);
        // companyRepository.findBy
    }

    public List<InvoiceDto> getCompaniesInvoiceByMonthAndName(@CheckName String name, String month, ModelMapper model) {

        return companyRepository.findByName(name).getInvoices()
                .stream().filter(s -> s.getRegistrationDate().getMonth().equals(Month.valueOf(month.toUpperCase(Locale.ROOT))))
                .map(s -> model.map(s, InvoiceDto.class))
                .collect(Collectors.toList());


    }

    public List<InvoiceDto> getCompaniesInvoiceByName(String name, ModelMapper model) {

        return companyRepository.findByName(name).getInvoices().stream().map(s -> model.map(s, InvoiceDto.class))
                .collect(Collectors.toList());
    }
}

