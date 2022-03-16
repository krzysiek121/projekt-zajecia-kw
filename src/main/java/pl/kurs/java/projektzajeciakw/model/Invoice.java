package pl.kurs.java.projektzajeciakw.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company owner;

    public Invoice(String country, double amount, LocalDate registrationDate, Company owner ) {
        this.country = country;
        this.amount = amount;
        this.registrationDate = registrationDate;
        this.owner = owner;
        this.owner.getInvoices().add(this);
    }
}

