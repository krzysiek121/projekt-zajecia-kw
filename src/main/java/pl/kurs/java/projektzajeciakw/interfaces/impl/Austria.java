package pl.kurs.java.projektzajeciakw.interfaces.impl;


import org.springframework.stereotype.Service;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
@Service
public class Austria implements ICountry {
    @Override
    public String getName() {
        return "AT";
    }

    @Override
    public double calculate(double a) {
        return (a * 0.20)/(1+0.20);
    }
}
