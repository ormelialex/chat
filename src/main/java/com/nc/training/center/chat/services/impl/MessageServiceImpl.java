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
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ChatRepository chatRepo;
    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private UserRepository userRepo;
    //@Autowired
    //private ChatRepository chatRepo;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public void createMessage(User sender,User recipient,String message, Chat chat) {
            Message createMessage = new Message();
            Chat newChat = new Chat();
            newChat.setTitle("Test");
            newChat.setUsers(Arrays.asList(sender,recipient));
            Chat chatFromBD=chatRepo.findByUsers(Arrays.asList(sender,recipient));
            if(chatFromBD!=null){
                createMessage.setChat(chatFromBD);
            }else{
                createMessage.setChat(newChat);
            }
            createMessage.setChat(chat);
            createMessage.setMsg(message);
            createMessage.setSendDate(LocalDateTime.now());
            createMessage.setSender(sender);
            createMessage.setRecipient(recipient);
            messageRepo.saveAndFlush(createMessage);
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
    public List<Message> getAllMessagesFromPrivateChat(List<User> users) {
/*        Chat chat = new Chat();
        chat.setTitle("Test");
        chat.setUsers(users);
        Message message1 = new Message();
        message1.setSendDate(LocalDateTime.now());
        message1.setRecipient(userRepo.findByLogin(users.get(0).getLogin()));
        message1.setSender(userRepo.findByLogin(users.get(1).getLogin()));
        message1.setMsg("Hello");
        message1.setChat(null);
        messageRepo.save(message1);*/
        Chat chatFromBD = chatRepo.findByUsers(users);
        List<Message> allMessages = messageRepo.findAllByChat(chatFromBD);
        return allMessages;
    }


}
