package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.ChatRepository;
import com.nc.training.center.chat.services.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepo;


    @Override
    public Chat getChatByUsers(Set<User> users) {
        return chatRepo.findByUsers(users);
    }
}
