package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List <User> findByName(String name);

    //List <User> findById(String name);

    //List <User> findAll(String name);
}
