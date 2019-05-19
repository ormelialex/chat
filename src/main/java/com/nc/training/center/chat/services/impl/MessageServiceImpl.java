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
import java.util.List;

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
    public void createMessage(String message,String chat_id,User sender) {
            Message createMessage = new Message();
            Chat chat =chatRepo.findByChatId(Long.parseLong(chat_id));
            createMessage.setMsg(message);
            createMessage.setSendDate(LocalDateTime.now());
            createMessage.setSender(sender);
            createMessage.setChat(chat);
            messageRepo.save(createMessage);
    }

    @Override
    public Message getMessageBySender(User sender) {
        return messageRepo.findBySender(sender);
    }

    @Override
    public List<Message> getAllMessagesFromPrivateChat(String chat_id) {
        Chat chatFromBD = chatRepo.findByChatId(Long.parseLong(chat_id));
        List<Message> allMessages = messageRepo.findAllByChat(chatFromBD);
        return allMessages;
    }


}
