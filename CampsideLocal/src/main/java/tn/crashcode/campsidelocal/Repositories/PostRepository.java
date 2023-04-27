package tn.crashcode.campsidelocal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.crashcode.campsidelocal.Entities.ForumSection;
import tn.crashcode.campsidelocal.Entities.Post;
import tn.crashcode.campsidelocal.Entities.User;

import java.util.List;
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByForumSection(ForumSection section);
    List<Post> findAllByUser(User user);
    @Query("SELECT p FROM Post p WHERE YEAR(p.datePost) = :year")
    List<Post> findAllByDatePost_Year(@Param("year")int y);

    List<Post> findByContentContainingIgnoreCase(String s);

    List<Post> findByUser_UsernameIgnoreCase(String  UserName);

    List<Post> findByContentContainingIgnoreCaseAndUser_UsernameIgnoreCase(String content,String username);


}
