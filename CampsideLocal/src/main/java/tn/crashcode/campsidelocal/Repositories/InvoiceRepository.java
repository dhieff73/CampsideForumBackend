package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.crashcode.campsidelocal.Entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    // Add any additional methods specific to the Invoice entity if needed
}
