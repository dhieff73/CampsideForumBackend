package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.crashcode.campsidelocal.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // add any additional methods specific to this repository
}
