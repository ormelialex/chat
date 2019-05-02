package com.nc.training.center.chat.services.impl;

import com.google.common.hash.Hashing;
import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public User findByUserNameAndPassword(String login, String password) {
        String encPass = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString().toUpperCase();
        return userRepo.findByLoginAndPassword(login, encPass);
    }

    @Override
    public User getUserByLogin(String login){
        return userRepo.findByLogin(login);
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    @Override
    public User create(String login, String password, byte age, LocalDate birthday) {
        if (userRepo.existsByLogin(login)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        String encPass = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString().toUpperCase();
        User createUser = new User();
        createUser.setLogin(login);
        createUser.setAge(age);
        createUser.setBirthday(birthday);
        createUser.setRole(Role.USER);
        createUser.setRegistrationDay(LocalDate.now());
        createUser.setPassword(encPass);
        return userRepo.save(createUser);
    }
}
