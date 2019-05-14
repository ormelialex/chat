package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;

public interface MessageService {

    void createMessage(User sender,User recipient,String message, Chat chat);

    Message getMessageBySender(User sender);

    Message getMessageByRecipient(User recipient);

    Iterable<Message> getAllMessagesFromPrivateChat(String sender,String recipient);

}
