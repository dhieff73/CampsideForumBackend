package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.crashcode.campsidelocal.Entities.Comment;
import tn.crashcode.campsidelocal.Entities.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findCommentsByPost(Post post);
    List<Comment> findByUsername(String username);
}
