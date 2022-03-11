package pl.kurs.java.projektzajeciakw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kurs.java.projektzajeciakw.model.Invoice;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
