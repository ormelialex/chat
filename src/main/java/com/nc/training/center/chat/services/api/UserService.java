package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

import java.time.LocalDate;

public interface UserService {

    User findByUserNameAndPassword(String login, String password);

    User create(String login, String password, byte age, LocalDate birthday);
}
