package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.ChatRepository;
import com.nc.training.center.chat.services.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepo;

    @Override
    public Chat getChatByUsers(Set<User> users) {
        return chatRepo.findByUsers(users);
    }

    @Override
    public List<Chat> getChatsWithUser(User user) {
        return chatRepo.findByUser(user);
    }

    @Override
    public Chat getChatById(Long Id) {
        return chatRepo.findByChatId(Id);
    }

    @Override
    public void createChat(String title, Set<User> users) {
        Chat createChat = new Chat();
        createChat.setTitle(title);
        createChat.setUsers(users);
        chatRepo.save(createChat);
    }

}
