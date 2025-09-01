package com.example.demo.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.example.demo.entity.chat;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ChatRepository;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ChatService {
    private ChatRepository chatRepository;
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    public ChatService(ChatRepository chatRepository){
        this.chatRepository=chatRepository;
    }
    public List<chat> getChat(Integer page,String url) {  // 这里将int改为Integer，适配控制器的参数类型
        List<chat> chats = chatRepository.getChat(page,url);
        
        // 遍历聊天列表并打印每个chat的信息
        if (chats != null && !chats.isEmpty()) {
            logger.info("获取到 {} 条聊天记录，分页参数：{}", chats.size(), page);
            for (chat chat : chats) {
                logger.info("评论信息：{} {}",chat.getUsername(),chat.getContent());
            }
        } else {
            logger.info("未获取到聊天记录，分页参数：{}", page);
        }
        
        return chats;
    }
    public void insertChat(String username,String content,String url){
        chatRepository.insertChat(username,content,url);
    }
}
