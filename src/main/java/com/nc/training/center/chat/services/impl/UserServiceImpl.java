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
        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String encPass = passwordEncoder.encode(password);
        String encPass = bCryptPasswordEncoder.encode(password);
        return userRepo.findByLoginAndPassword(login, encPass);
    }

    @Override
    public User getUserByLogin(String login){
        return userRepo.findByLogin(login);
    }

    @Override
    public void create(String login, String password, LocalDate birthday, byte age) {
        if (userRepo.existsByLogin(login)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        //String encPass = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString().toUpperCase();
        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User createUser = new User();
        createUser.setLogin(login);
        createUser.setAge(age);
        createUser.setBirthday(birthday);
        createUser.setRole(Role.USER);
        createUser.setRegistrationDay(LocalDate.now());
        createUser.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(createUser);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Founded");
        }
        return new UserDetailImpl(user);
    }
}