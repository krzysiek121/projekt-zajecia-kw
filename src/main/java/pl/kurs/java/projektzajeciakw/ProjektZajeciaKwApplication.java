package pl.kurs.java.projektzajeciakw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"pl.kurs"})
public class ProjektZajeciaKwApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektZajeciaKwApplication.class, args);
    }

}
