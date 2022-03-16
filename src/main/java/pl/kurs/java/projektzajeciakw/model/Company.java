package pl.kurs.java.projektzajeciakw.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode(exclude = {"invoices"})
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL})
    private Set<Invoice> invoices = new HashSet<>();

    public Company(String name) {
        this.name = name;
    }
}
