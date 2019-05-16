package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;

import java.util.Set;

public interface ChatService {

    Chat getChatByUsers(Set<User> users);

}
