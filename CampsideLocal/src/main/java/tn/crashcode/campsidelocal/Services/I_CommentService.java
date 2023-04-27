package tn.crashcode.campsidelocal.Services;

import tn.crashcode.campsidelocal.Entities.Comment;
import tn.crashcode.campsidelocal.Entities.Post;

import java.util.List;

public interface I_CommentService {

    Comment CreateComment(Comment p);
    void DeleteComment(int  idComment);
    Comment UpdateComment(Comment p);
    Comment RetriveComment(int  id);

    List<Comment> RetriveAllComments();

    void AddCommentAndAssignToPostAndUser(Comment comment,int idUser,int idPost);

    List<Comment> SortCommentsByReactions(int idpost);

    List<Comment> SortCommentsByDate(int idpost);


}
