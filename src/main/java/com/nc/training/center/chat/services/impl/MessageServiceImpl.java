package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    /*@Autowired
    private MessageRepository messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Message getMessageBySender(User sender) {
        return messageRepo.findBySender(sender);
    }

    @Override
    public Message getMessageByRecipient(User recipient) {
        return messageRepo.findBySender(recipient);
    }

    @Override
    public Iterable<Message> getAllMessagesFromChat(Chat chat) {
            return messageRepo.findAllByChat(chat);
    }*/
}
