package tn.crashcode.campsidelocal.Services;

import tn.crashcode.campsidelocal.Entities.Message;

import java.util.List;

public interface I_MessageService {
    Message CreateMessage(Message message);
    void DeleteMessage(int  idMessage);
    Message UpdateMessage(Message message);
    Message RetriveMessage(int  id);
    List<Message> RetriveAllMessages();

    void AddMessageAndAssignToUserAndChatroom(Message message,int idChatroom,int idUSer);

}
