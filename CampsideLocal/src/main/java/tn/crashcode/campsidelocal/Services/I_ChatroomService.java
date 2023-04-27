package tn.crashcode.campsidelocal.Services;

import tn.crashcode.campsidelocal.Entities.Chatroom;
import tn.crashcode.campsidelocal.Entities.User;

import java.util.List;

public interface I_ChatroomService {

    Chatroom CreateChatroom(Chatroom cr);
    void DeleteChatroom(int  idChatroom);
    Chatroom UpdateChatroom(Chatroom p);
    Chatroom RetriveChatroom(int  id);

    List<Chatroom> RetriveAllChatrooms();
    public boolean JoinChatroom(User U,Chatroom C);

    public  void ExitChatroom(User U, Chatroom C);


}
