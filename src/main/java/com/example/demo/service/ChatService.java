package com.example.demo.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.example.demo.entity.chat;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ChatRepository;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ChatService {
    private ChatRepository chatRepository;
    public ChatService(ChatRepository chatRepository){
        this.chatRepository=chatRepository;
    }
    public List<chat>getChat(int page){
        return chatRepository.getChat(page);
    }
    public void insertChat(String username,String content){
        chatRepository.insertChat(username,content);
    }
}
