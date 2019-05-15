package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Message;
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

import java.time.LocalDateTime;
import java.util.Arrays;

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
        model.addAttribute("sender",activeUser.getUsername());
        model.addAttribute("recipient",login);
        model.addAttribute("messages",messageService.getAllMessagesFromPrivateChat(Arrays.asList(userService.getUserByLogin(login),userService.getUserByLogin(activeUser.getUsername()))));
        return "privateChat";
    }

    @PostMapping("/privateChat/{login}")
    public String addMessage(Message message, Model model){
        message.setSendDate(LocalDateTime.now());
        messageService.createMessage(message.getSender(),message.getRecipient(),message.getMsg(),message.getChat());
        model.addAttribute("sended","Вы успешно отправили сообщение!!!");
        return "privateChat";
    }
}
