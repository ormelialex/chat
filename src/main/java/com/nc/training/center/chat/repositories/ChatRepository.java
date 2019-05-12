package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    Chat findByTitle(String title);

    Chat findByUsers(List<User> users);

}
