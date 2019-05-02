package com.nc.training.center.chat.controllers;

import com.google.common.hash.Hashing;
import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;


@Controller
public class UserController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserService userService;
    //to get login form page

    @GetMapping("/login")
    public String redirectError(@RequestParam(name="msg",defaultValue = "no") String msg, Model model){
        if(msg.equals("failure")){
            model.addAttribute("error","Неправильный логин или пароль!!!");
        }
        if(msg.equals("denied")){
            model.addAttribute("error","Вы заблокированы!!!");
        }
        return "login";
    }

/*    //checking login credentials
    @PostMapping("/login")
    public String login(User user, Model model){
        User userFromDb=userService.getUserByLogin(user.getLogin());
        if (user.equals(userFromDb)){
            //if data is correct
            return "home";
        }
        return "login";
    }*/


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("request","/registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb=userService.getUserByLogin(user.getLogin());
        if(userFromDb!=null){
            model.addAttribute("message","true");
            model.addAttribute("user",user);
            return "registration";
        }
        user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString().toUpperCase());
        user.setRole(Role.USER);
        user.setRegistrationDay(LocalDate.now());
        userService.saveUser(user);
        model.addAttribute("success","Вы успешно зарегистрировались!!!");
        return "home";
    }

}
