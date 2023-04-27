package tn.crashcode.campsidelocal.Services;

import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tn.crashcode.campsidelocal.Entities.*;
import tn.crashcode.campsidelocal.Repositories.*;
import java.time.Year;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class PostService implements I_PostService{


    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ForumSectionRepository forumSectionRepository;
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    CommentRepository commentRepository;

    public Post CreatePost(Post p)
    {
        return  postRepository.save(p);
    }

    public void DeletePost(int p)
    {
        postRepository.deleteById(p);
    }
    public Post UpdatePost(Post p)
    {
        return  postRepository.save(p);

    }
    public Post RetrivePost(int  id)
    {
        return  postRepository.findById(id).get();
    }
    public List<Post> RetriveAllPosts()
    {
       return  postRepository.findAll();
    }
    public void AddPostAndAssignToUser(Post post,int idUser,int idSection){
        post.setForumSection(forumSectionRepository.findById(idSection).get());
        post.setUser(userRepository.findById(idUser).get());
        postRepository.save(post);
    }

    @Override
    public List<Post> SortPostsByReacts(int idsection) {
        ForumSection section=forumSectionRepository.findById(idsection).get();

        List<Post> posts=postRepository.findAllByForumSection(section);
        Map<Post,Integer> PostMap=new HashMap<>();

        for (Post p:posts){
            PostMap.put(p,reactionRepository.findAllByPost(p).size()+commentRepository.findCommentsByPost(p).size());
        }
        Map<Post, Integer> sortedMap = PostMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<Post>SortedComments= new ArrayList<>(sortedMap.keySet());

        Collections.reverse(SortedComments);

        return SortedComments;
    }

    @Override
    public List<Post> SortPostsByDate(int idsection) {
        ForumSection section=forumSectionRepository.findById(idsection).get();
        List<Post> posts=postRepository.findAllByForumSection(section);
        Map<Post,Date> PostMap=new HashMap<>();

        for (Post p:posts){
            PostMap.put(p,p.getDatePost());
        }
        Map<Post, Date> sortedMap = PostMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<Post>SortedComments= new ArrayList<>(sortedMap.keySet());

        Collections.reverse(SortedComments);

        return SortedComments;
    }

    @Override
    public int NumberOfPostsByUser(int idUser) {
        User user=userRepository.findById(idUser).get();
        return postRepository.findAllByUser(user).size();
    }
    @Override
    public Map<Integer, List<Post>> PostsPerMonthOfYear(int year) {
        Map<Integer,List<Post>> postMap=new HashMap<>();
        List<Post> PostOfyear=postRepository.findAllByDatePost_Year(year);
        for (int i =1;i<=12;i++){
            List<Post> postOfMonth = new ArrayList<>();
            for(Post p : PostOfyear){
                if(p.getDatePost().toLocalDateTime().toLocalDate().getMonthValue()==i){
                    postOfMonth.add(p);
                }
                postMap.put(i,postOfMonth);
            }
        }
        return postMap;
    }
    @Override
    public List<Post> PostsOfyear(int year) {
        List<Post>postsOfYear=new ArrayList<>();

        for(Post p:postRepository.findAll()){
            if (p.getDatePost().toLocalDateTime().toLocalDate().getYear()==2023){
                postsOfYear.add(p);
            }
        }
        return postsOfYear;
    }
    @Override
    public List<Post> MultiCritaryPostsearch(String content, String username) {
        List<Post> posts;
        if (content!=null&&username!=null){
            posts=postRepository.findByContentContainingIgnoreCaseAndUser_UsernameIgnoreCase(content,username);
        } else if (username!=null) {
            posts=postRepository.findByUser_UsernameIgnoreCase(username);
        } else if (content!=null) {
            posts=postRepository.findByContentContainingIgnoreCase(content);
        }
        else {
            posts=Collections.emptyList();
        }
        return posts;
    }

    Comparator<Object> comparator=(o1, o2) ->{

        if (o1 instanceof Post && o2 instanceof Comment){
            return (((Post) o1).getDatePost().compareTo(((Comment) o2).getDateComment()));
        } else if (o1 instanceof Post && o2 instanceof Reaction) {
            return (((Post) o1).getDatePost().compareTo(((Reaction) o2).getDateReaction()));
        } else if (o1 instanceof Comment && o2 instanceof Reaction) {
            return (((Comment) o1).getDateComment().compareTo(((Reaction) o2).getDateReaction()));
        } else if (o1 instanceof Post && o2 instanceof Post) {
            return (((Post) o1).getDatePost().compareTo(((Post) o2).getDatePost()));
        }
         else if (o1 instanceof Reaction && o2 instanceof Reaction) {
             return (((Reaction) o1).getDateReaction().compareTo(((Reaction) o2).getDateReaction()));
        }
         else if (o1 instanceof Comment && o2 instanceof Comment) {
            return (((Comment) o1).getDateComment().compareTo(((Comment) o2).getDateComment()));
        }
         else {
            throw new IllegalArgumentException("Objects are not of the same type");
        }
    };

    @Override
    public List<Object> PersonalHistory(int idUser) {

        List<Object> History=new ArrayList<>();
        User user=userRepository.findById(idUser).get();
        List<Post> posts=postRepository.findAllByUser(user);
        List<Comment> comments=commentRepository.findByUsername(user.getUsername());
        List<Reaction> reactions=reactionRepository.findByUsername(user.getUsername());
        Collections.addAll(History,posts);
        Collections.addAll(History,comments);
        Collections.addAll(History,reactions);

        History.sort(comparator);

        return History;
    }

}
