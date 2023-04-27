package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.crashcode.campsidelocal.Entities.Comment;
import tn.crashcode.campsidelocal.Entities.Post;
import tn.crashcode.campsidelocal.Entities.Reaction;

import java.util.List;


public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByComment(Comment comment);
    List<Reaction> findByUsername(String s);
}
