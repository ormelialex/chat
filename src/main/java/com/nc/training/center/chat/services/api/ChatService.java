package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;

import java.util.List;
import java.util.Set;

public interface ChatService {

    Chat getChatByUsers(Set<User> users);

    List<Chat> getChatsWithUser(User user);

    Chat getChatById(Long Id);

    void createChat(String title, List<String> users);
}
