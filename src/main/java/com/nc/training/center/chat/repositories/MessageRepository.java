package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    Message findByRecipient(User recipient);

    Message findBySender(User sender);

    Iterable<Message> findAllByChat(Chat chat);

    void saveToChat(Message message,Chat chat);

}
