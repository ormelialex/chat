package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    /*@Autowired
    ChatService chatService;*/

    @GetMapping("/privateChat/{login}")
    public String privateChat(@PathVariable String login, @AuthenticationPrincipal org.springframework.security.core.userdetails.User activeUser,Model model){
        model.addAttribute("sender",activeUser.getUsername());
        model.addAttribute("recipient",login);
        model.addAttribute("messages",messageService.getAllMessagesFromChat(activeUser.getUsername(),login));
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
