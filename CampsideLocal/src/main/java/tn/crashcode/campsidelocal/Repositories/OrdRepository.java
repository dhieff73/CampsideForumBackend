package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.crashcode.campsidelocal.Entities.Ord;

@Repository
public interface OrdRepository extends JpaRepository<Ord, Integer> {
    // Add any additional methods specific to the Order entity if needed
}
