package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.MessageRepository;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepo;
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
    public Iterable<Message> getAllMessagesFromChat(Chat chat) {
            return messageRepo.findAllByChat(chat);
    }

}
