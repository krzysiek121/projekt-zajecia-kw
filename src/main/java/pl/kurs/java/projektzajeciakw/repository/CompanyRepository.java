package pl.kurs.java.projektzajeciakw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.java.projektzajeciakw.model.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByName(String name);
}
