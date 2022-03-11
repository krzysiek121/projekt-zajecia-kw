package pl.kurs.java.projektzajeciakw.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;
    private double amount;
    private LocalDate registrationDate;

    public Invoice(String country, double amount, LocalDate registrationDate) {
        this.country = country;
        this.amount = amount;
        this.registrationDate = registrationDate;
    }
}

