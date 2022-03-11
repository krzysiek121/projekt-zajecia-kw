package pl.kurs.java.projektzajeciakw.interfaces.impl;

import org.springframework.stereotype.Service;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
@Service
public class France implements ICountry {
    @Override
    public String getName() {
        return "FR";
    }

    @Override
    public double calculate(double a) {
        return (a * 0.20)/(1+0.20);
    }
}
