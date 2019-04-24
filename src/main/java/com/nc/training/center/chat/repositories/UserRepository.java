package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String name, String password);

    boolean existsByLogin(String name);

}
