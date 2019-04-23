package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccountController {
    @Autowired
    UserService userService;
    @GetMapping("/foo")
    public String foo(){
        return "foo";
    }
}
