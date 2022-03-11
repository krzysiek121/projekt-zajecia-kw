package pl.kurs.java.projektzajeciakw.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Map;

@Value
@Getter
@Setter
public class AllTaxResponse {

    private Map<String, Double> mapCountryTax;
    private double summaryTax;
}
