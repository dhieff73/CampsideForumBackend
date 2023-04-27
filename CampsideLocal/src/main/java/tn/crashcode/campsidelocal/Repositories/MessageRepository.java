package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.crashcode.campsidelocal.Entities.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
