package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Chat;
import com.nc.training.center.chat.services.api.ChatService;
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

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;

    @GetMapping("/privateChat/{chat_id}")
    public String privateChat(@PathVariable String chat_id, @AuthenticationPrincipal UserDetails activeUser, Model model){
        model.addAttribute("sender",activeUser.getUsername());
        model.addAttribute("chat",chatService.getChatById(Long.parseLong(chat_id)));
        model.addAttribute("messages",messageService.getAllMessagesFromPrivateChat(chat_id));
        return "privateChat";
    }

    @PostMapping("/privateChat/{chat_id}")
    public String addMessage(String message,@PathVariable String chat_id, @AuthenticationPrincipal UserDetails activeUser, Model model){
        messageService.createMessage(message,chat_id,userService.getUserByLogin(activeUser.getUsername()));
        model.addAttribute("sended","Вы успешно отправили сообщение!!!");
        return "redirect:/privateChat/{chat_id}";
    }

    @PostMapping("/createChat")
    public String addChat(String title,/*here will be info 'bout checkboxes*/,Model model){
        Chat chatFromBD = chatService.getChatByUsers(/*set users from checkboxes*/);
        if(chatFromBD!=null){
            model.addAttribute("chatError","This Chat is already exist");
        }/*обработать исключение*/
        chatService.createChat(title,users);
        model.addAttribute("success","Вы успешно зарегистрировались!!!");
        return "home";
    }
}
