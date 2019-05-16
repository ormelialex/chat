package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;

import java.util.List;
import java.util.Set;

public interface MessageService {

    void createMessage(Message message);

    Message getMessageBySender(User sender);

    Message getMessageByRecipient(User recipient);

    List<Message> getAllMessagesFromPrivateChat(Set<User> users);

}
