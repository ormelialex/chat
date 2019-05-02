package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    User findByLoginAndPassword(String name, String password);

    boolean existsByLogin(String name);

}
