package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {

    Message findByChat(Chat chat);

    Message findByRecipient(User recipient);

    Message findBySender(User sender);

    Iterable<Message> findAllByChat(Chat chat);
}
