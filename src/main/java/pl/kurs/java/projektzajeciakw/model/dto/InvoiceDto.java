package pl.kurs.java.projektzajeciakw.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDto {

    private double amountWithoutTax;
    private LocalDate registrationDate;
    private String country;
}
