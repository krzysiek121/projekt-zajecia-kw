package pl.kurs.java.projektzajeciakw.interfaces.impl;


import org.springframework.stereotype.Service;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
@Service
public class Italy implements ICountry {
    @Override
    public String getName() {
        return "IT";
    }

    @Override
    public double calculate(double a) {
        return 0;
    }
}
