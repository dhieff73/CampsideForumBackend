package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.crashcode.campsidelocal.Entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
