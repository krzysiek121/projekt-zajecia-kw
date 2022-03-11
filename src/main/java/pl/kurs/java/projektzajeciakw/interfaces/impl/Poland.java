package pl.kurs.java.projektzajeciakw.interfaces.impl;


import org.springframework.stereotype.Service;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
@Service
public class Poland implements ICountry {
    @Override
    public String getName() {
        return "PL";
    }

    @Override
    public double calculate(double a) {
        return (a * 0.23)/(1+0.23);
    }
}
