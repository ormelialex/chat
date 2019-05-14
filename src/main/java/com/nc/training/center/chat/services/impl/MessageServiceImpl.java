package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.MessageRepository;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
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
    public Iterable<Message> getAllMessagesFromChat(String senderMessage,String recipientMessage) {
        /*
        Message message1 = new Message();
        message1.setSendDate(LocalDateTime.now());
        message1.setRecipient(userRepo.findByLogin(recipientMessage));
        message1.setSender(userRepo.findByLogin(senderMessage));
        message1.setMsg("Hello");
        message1.setChat(null);
        messageRepo.save(message1);
        */
        User sender = userRepo.findByLogin(senderMessage);
        User recipient = userRepo.findByLogin(recipientMessage);
        List<Message> allMessages = new ArrayList<>();
        allMessages = messageRepo.findAll();
        List<Message> allMessagesFromChat = new ArrayList<>();
        for (Message message: allMessages
             ) { if((message.getSender().equals(sender)&&message.getRecipient().equals(recipient))||(message.getSender().equals(recipient)&&message.getRecipient().equals(sender))){
                allMessagesFromChat.add(message);
        }

        }
            return allMessagesFromChat;
    }


}
