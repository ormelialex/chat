package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.ChatRepository;
import com.nc.training.center.chat.services.api.ChatService;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepo;
    @Autowired
    UserService userService;

    @Override
    public Chat getChatByUsers(Set<User> users) {
        return chatRepo.findByUsers(users);
    }

    @Override
    public List<Chat> getChatsWithUser(User user) {
        return chatRepo.findAllByUsersContains(user);
    }

    @Override
    public Chat getChatById(Long Id) {
        return chatRepo.findChatById(Id);
    }

    @Override
    public void createChat(String title, List<String> users) {
        Set<User> chatUsers = new HashSet<>();
        for (String s:users
             ) {
                chatUsers.add(userService.getUserByLogin(s));
        }
        Chat createChat = new Chat();
        createChat.setTitle(title);
        createChat.setUsers(chatUsers);
        chatRepo.save(createChat);
    }

}
