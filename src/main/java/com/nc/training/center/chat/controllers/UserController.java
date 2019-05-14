package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
/*    @Autowired
    UserRepository userRepo;*/
    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        userService.create(user.getLogin(),user.getPassword(),user.getBirthday(),user.getAge());
        model.addAttribute("success","Вы успешно зарегистрировались!!!");
        return "home";
    }


    @GetMapping("/home")
    //@PreAuthorize("hasAuthority('USER')")
    public String home(@AuthenticationPrincipal org.springframework.security.core.userdetails.User activeUser, Model model){
        //UserDetailImpl user = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("lk",activeUser.getUsername());
        model.addAttribute("users",userService.getAllUsers());
        return "home";
    }

}
