package pl.kurs.java.projektzajeciakw.interfaces.impl;


import org.springframework.stereotype.Service;
import pl.kurs.java.projektzajeciakw.interfaces.ICountry;
@Service
public class Germany implements ICountry {
    @Override
    public String getName() {
        return "DE";
    }

    @Override
    public double calculate(double a) {
        return (a * 0.19)/(1+0.19);
    }
}
