package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.ChatRepository;
import com.nc.training.center.chat.repositories.MessageRepository;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ChatRepository chatRepo;
    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public void createMessage(Message message) {
            Message createMessage = new Message();
            Set<User> usersSet = new HashSet<>(Arrays.asList(message.getSender(),message.getRecipient()));
            Chat chatFromBD=chatRepo.findByUsers(usersSet);
            if(chatFromBD!=null){
                createMessage.setChat(chatFromBD);
            }else{
                Chat newChat = new Chat();
                newChat.setUsers(usersSet);
                newChat.setTitle("PrivateChat");
                createMessage.setChat(newChat);
            }
            createMessage.setMsg(message.getMsg());
            createMessage.setSendDate(LocalDateTime.now());
            createMessage.setSender(message.getSender());
            createMessage.setRecipient(message.getSender());
            messageRepo.save(createMessage);
    }

    @Override
    public Message getMessageBySender(User sender) {
        return messageRepo.findBySender(sender);
    }

    @Override
    public Message getMessageByRecipient(User recipient) {
        return messageRepo.findByRecipient(recipient);
    }

    @Override
    public List<Message> getAllMessagesFromPrivateChat(Set<User> users) {
        Chat chatFromBD = chatRepo.findByUsers(users);
        List<Message> allMessages = messageRepo.findAllByChat(chatFromBD);
        return allMessages;
    }


}
