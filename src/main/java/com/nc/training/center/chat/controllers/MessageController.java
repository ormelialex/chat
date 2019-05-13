package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class MessageController {
/*    @Autowired
    MessageRepository messageRepo;*/
    @Autowired
    MessageService messageService;
    /*@Autowired
    ChatService chatService;*/

    @GetMapping("/privateChat")
    public String privateChat(Model model){
        model.addAttribute("chat","/privateChat");
        return "privateChat";
    }

    @PostMapping("/privateChat")
    public String addMessage(Message message, Model model){
        message.setSendDate(LocalDateTime.now());
        messageService.createMessage(message.getSender(),message.getRecipient(),message.getMsg(),message.getChat());
        model.addAttribute("sended","Вы успешно отправили сообщение!!!");
        return "privateChat";
    }
}
