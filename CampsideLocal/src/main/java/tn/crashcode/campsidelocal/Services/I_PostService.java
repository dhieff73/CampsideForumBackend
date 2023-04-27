package tn.crashcode.campsidelocal.Services;

import tn.crashcode.campsidelocal.Entities.ForumSection;
import tn.crashcode.campsidelocal.Entities.Post;
import tn.crashcode.campsidelocal.Entities.User;

import java.sql.Timestamp;
import java.util.*;

public interface I_PostService {
    Post CreatePost(Post p);
    void DeletePost(int  idpost);
    Post UpdatePost(Post p);
    Post RetrivePost(int  id);
    List <Post> RetriveAllPosts();

    void AddPostAndAssignToUser(Post post,int idUser,int idSection);

    List<Post> SortPostsByReacts(int idsection);

    List<Post> SortPostsByDate(int idsection);

    int NumberOfPostsByUser(int idUser);

    Map<Integer,List<Post>> PostsPerMonthOfYear(int year);


    List<Post> PostsOfyear(int year);

    List<Post> MultiCritaryPostsearch(String content,String username);

    List<Object> PersonalHistory(int idUser);








}
