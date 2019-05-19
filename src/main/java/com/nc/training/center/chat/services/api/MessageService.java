package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;

import java.util.List;

public interface MessageService {

    void createMessage(String message,String chat_id,User sender);

    Message getMessageBySender(User sender);

    List<Message> getAllMessagesFromPrivateChat(String chat_id);

}
