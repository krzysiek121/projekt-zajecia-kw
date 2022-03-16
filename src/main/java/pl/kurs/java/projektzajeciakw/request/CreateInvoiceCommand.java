package pl.kurs.java.projektzajeciakw.request;

import lombok.Data;
import pl.kurs.java.projektzajeciakw.model.Company;


import javax.validation.constraints.NotEmpty;


@Data
public class CreateInvoiceCommand {
   @NotEmpty(message = "COUNTRY_NOT_EMPTY")
    private String country;
    @NotEmpty(message = "AMOUNT_NOT_EMPTY")
    private double amount;
    @NotEmpty(message = "COMPANY_NOT_EMPTY")
    private Company company;

}
