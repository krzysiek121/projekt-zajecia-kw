package pl.kurs.java.projektzajeciakw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.java.projektzajeciakw.ProjektZajeciaKwApplication;
import pl.kurs.java.projektzajeciakw.model.Company;
import pl.kurs.java.projektzajeciakw.model.Invoice;
import pl.kurs.java.projektzajeciakw.repository.CompanyRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProjektZajeciaKwApplication.class)
@AutoConfigureMockMvc
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CompanyRepository companyRepository;

    public void addObjects() {
        Company s1 = new Company("Karad");
        new Invoice("PL", 300, LocalDate.now(), s1);
        new Invoice("PL", 200, LocalDate.now(), s1);
        new Invoice("DE", 300, LocalDate.now(), s1);
        new Invoice("FR", 200, LocalDate.now(), s1);
        companyRepository.saveAndFlush(s1);
    }

    @Test
    public void shouldSaveCompanyAndInvoices() throws Exception {

        addObjects();
        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoice/taxSummary/Karad")
                //.content(history)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(4));

    }

    @Test
    public void shouldGetSizeOfMapTaxResultsByCompanyName() throws Exception {
        addObjects();
        String pl = "PL";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoice/taxSummary/Karad/March/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mapCountryTax.size()").value(3));
    }

    @Test
    public void shouldGetSummaryTaxResult() throws Exception {
        addObjects();
        String pl = "PL";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoice/taxSummary/Karad/March/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summaryTax").value(174));
    }

    @Test
    public void shouldGetMapRowRightResult() throws Exception {
        addObjects();
        String pl = "PL";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoice/taxSummary/Karad/March/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mapCountryTax.get(\"PL\")").value(93));
    }
}