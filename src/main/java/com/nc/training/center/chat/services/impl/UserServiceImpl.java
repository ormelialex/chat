package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.EnumSet;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUserNameAndPassword(String login, String password) {
        String encPass = bCryptPasswordEncoder.encode(password);
        return userRepo.findByLoginAndPassword(login, encPass);
    }

    @Override
    public User getUserByLogin(String login){
        return userRepo.findByLogin(login);
    }

    @Override
    public void create(User user) {
        if (userRepo.existsByLogin(user.getLogin())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        User createUser = new User();
        createUser.setLogin(user.getLogin());
        createUser.setAge(user.getAge());
        createUser.setBirthday(user.getBirthday());
        createUser.setRole(EnumSet.of(Role.USER));
        createUser.setRegistrationDay(LocalDate.now());
        createUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(createUser);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Founded");
        }
        //return new UserDetailImpl(user);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getRoles());
    }

    public Iterable<User> getAllUsers(){
        return userRepo.findAll();
    }
}