package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.crashcode.campsidelocal.Entities.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom,Integer> {
}
