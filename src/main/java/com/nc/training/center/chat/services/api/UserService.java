package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

public interface UserService {

    User findByUserNameAndPassword(String login, String password);

    void create(User user);

    User getUserByLogin(String login);

    Iterable<User> getAllUsers();

}
