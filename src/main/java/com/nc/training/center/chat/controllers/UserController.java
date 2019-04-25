package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    @Autowired
    UserService userService;
    //@GetMapping("/foo")
    /*public String foo(){
        return "foo";
    }*/
    //to get login form page
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
    //checking login credentials
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm")User user, Model model){
        String login = user.getLogin();
        String password = user.getPassword();

        if ("admin".equals(login)&&"admin".equals(password)){
            //if data is correct
            return "home";
        }
        model.addAttribute("invalidCredentials",true);
        return "login";
    }
}
