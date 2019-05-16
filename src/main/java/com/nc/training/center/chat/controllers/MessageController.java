package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.api.MessageService;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    /*@Autowired
    ChatService chatService;*/

    @GetMapping("/privateChat/{login}")
    public String privateChat(@PathVariable String login, @AuthenticationPrincipal UserDetails activeUser, Model model){
        Set<User> users = new HashSet<>(Arrays.asList(userService.getUserByLogin(login),userService.getUserByLogin(activeUser.getUsername())));
        model.addAttribute("sender",activeUser.getUsername());
        model.addAttribute("recipient",login);
        model.addAttribute("messages",messageService.getAllMessagesFromPrivateChat(users));
        return "privateChat";
    }

    @PostMapping("/privateChat/{login}")
    public String addMessage(String message,@PathVariable String login, @AuthenticationPrincipal UserDetails activeUser, Model model){
        Message resultMessage = new Message();
        resultMessage.setMsg(message);
        resultMessage.setSender(userService.getUserByLogin(login));
        resultMessage.setRecipient(userService.getUserByLogin(activeUser.getUsername()));
        messageService.createMessage(resultMessage);
        model.addAttribute("sended","Вы успешно отправили сообщение!!!");
        return "redirect:/privateChat/{login}";
    }
}
