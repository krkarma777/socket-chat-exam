package com.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/chatRoom")
    public String chatRoom() {
        return "chatRoom";
    }
    @GetMapping("/chatRoomMembers")
    public String chatRoomMembers() {
        return "chatRoomMembers";
    }
}
