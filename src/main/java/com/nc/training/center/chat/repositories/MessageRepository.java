package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    Message findByRecipient(User recipient);

    Message findBySender(User sender);

    List<Message> findAllByChat(Chat chat);

    //Iterable<Message> findAllByChat(Chat chat);

}
