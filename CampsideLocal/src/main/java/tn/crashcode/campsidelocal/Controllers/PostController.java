package tn.crashcode.campsidelocal.Controllers;
import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.crashcode.campsidelocal.Entities.Post;
import tn.crashcode.campsidelocal.Services.I_PostService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    I_PostService i_postService;
    @PostMapping("/add-post")
    public Post addPost(@RequestBody Post p)
    {
        return i_postService.CreatePost(p);
    }

    @PostMapping("/update-post")
    public Post modifyPost(@RequestBody Post p)
    {
        return  i_postService.UpdatePost(p);
    }

    @GetMapping("/retrieve-all-posts")
    public List<Post> getPosts()
    {
        List<Post> L=i_postService.RetriveAllPosts();
        return L;
    }

    @GetMapping("/retrieve-post/{post-id}")
    public Post getPost(@PathVariable("post-id") int idPost)
    {
        Post post =i_postService.RetrivePost(idPost);
        return post;
    }

    @DeleteMapping("/delete-post/{post-id}")
    public void deletePost(@PathVariable("post-id") int idPost)
    {
        i_postService.DeletePost(idPost);
    }

    @PostMapping("/add-assign-post/{user-id}/{section-id}")
    public void AddAndAssignPost(@RequestBody Post p,@PathVariable("user-id") int idUser,@PathVariable("section-id") int idSection){
        i_postService.AddPostAndAssignToUser(p,idUser,idSection);
    }

    @GetMapping("/posts-per-month/{year}")
    public Map<Integer,List<Post>> PostsPerMonth(@PathVariable("year") int year)
    {
        return i_postService.PostsPerMonthOfYear(year);
    }

    @GetMapping("/posts-ofyear/{year}")
    public List<Post>PostsOfYear(@PathVariable("year") int year)
    {
        List<Post> posts= i_postService.PostsOfyear(year);
        return  posts;
    }

    @GetMapping("/MultiSearch")
    public List<Post> SearchPosts(@RequestParam (name="content",required = false)String content,@RequestParam (name = "username",required = false)String username){
        return i_postService.MultiCritaryPostsearch(content,username);
    }
    @GetMapping("/History/{user-id}")
    public List<Object> History(@PathVariable("user-id") int UID){
        return i_postService.PersonalHistory(UID);
    }


}

