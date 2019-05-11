package com.nc.training.center.chat.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
/*    @Autowired
    MessageRepository messageRepo;
    @Autowired
    MessageService messageService;

    @GetMapping("/privateChat")
    public String registration(Model model){
        model.addAttribute("chat","/privateChat");
        return "privateChat";
    }*/
//(usersFromDB.get(0) == userList.get(0)) && (usersFromDB.get(1) == userList.get(1)) || (usersFromDB.get(0) == userList.get(1)) && (usersFromDB.get(1) == userList.get(0))
    /*@PostMapping("/privateChat")
    public String addMessage(User user1, User user2, Message message, Model model){
        List<User> userList = new ArrayList<User>();
        Chat chatFromDb = chatService.getChatByUsers(user1,user2);
        if(chatFromDb!=null) {
            добавить сообщение в чат
        }
        else{
        создать чат и добавить сообщение в чат
        }
        model.addAttribute("sended","Вы успешно отправили сообщение!!!");
        return "home";
    }*/
}
