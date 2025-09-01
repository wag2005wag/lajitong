package com.example.demo.controller;
import com.example.demo.entity.chat;
import com.example.demo.service.ChatService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final ChatService chatService;
    public ChatController(ChatService chatService){
        this.chatService=chatService;
    }
    @GetMapping
    public ResponseEntity<List<chat>>getChat(int page,String url){
        return ResponseEntity.ok(chatService.getChat(page,url));
    }
    @PostMapping("/add")
    public ResponseEntity<?>addChat(
        @RequestParam("content")String content,
        @RequestParam("url")String url,
        HttpSession session
    ){
        
        String username=(String)session.getAttribute("username");
        logger.info("评论发送者:{}",username);
        chatService.insertChat(username, content,url);
        return ResponseEntity.ok(Map.of("success", true, "message", "注册成功"));
    }

}
