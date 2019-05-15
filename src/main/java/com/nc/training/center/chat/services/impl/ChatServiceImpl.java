package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.ChatRepository;
import com.nc.training.center.chat.services.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepo;


    @Override
    public Chat getChatByUsers(List<User> users) {
        return chatRepo.findByUsers(users);
    }
}
