package com.nc.training.center.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/foo")
    public String foo(){
        return "fo2o";
    }
}
