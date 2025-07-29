package com.example.demo.controller;

import com.example.demo.entity.favourite;
import com.example.demo.entity.Passage;
import com.example.demo.service.FavouriteService;
import com.example.demo.service.PassageService;
import com.example.demo.service.UserService;

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
@RequestMapping("/favourite")
public class FavouriteController {
    private static final Logger logger = LoggerFactory.getLogger(FavouriteController.class);
    private final FavouriteService favouriteService;
    private final PassageService passageService;

    public FavouriteController(FavouriteService favouriteService,PassageService passageService){
        this.favouriteService=favouriteService;
        this.passageService=passageService;
    }

    @GetMapping
    public ResponseEntity<List<Passage>> getFavourite(HttpSession session){
        List<favourite>favourites=favouriteService.getFavourite((String)session.getAttribute("username"));
        logger.info("用户名{}",(String)session.getAttribute("username"));
        List<String>urls=new ArrayList<>();
        for(favourite fav:favourites){
            urls.add(fav.getUrl());
        }
        List<Passage>videos=new ArrayList<>();
        for(String url:urls){
            logger.info("封面网址{}",url);
            videos.add(passageService.GetPassageByUrl(url));
            logger.info("文章ID:{}",passageService.GetPassageByUrl(url).getId());
        }
        return ResponseEntity.ok(videos);
    }
    @PostMapping("/addFavourite")
    public ResponseEntity<?>addFavourite(@ModelAttribute FavouriteRequest request,HttpSession session){
        logger.info("用户名{}",(String)session.getAttribute("username"));
        favouriteService.insertFavourite((String)session.getAttribute("username"),request.getUrl());
        return ResponseEntity.ok(Map.of("success", true, "message", "注册成功"));
    }



}
class FavouriteRequest{
    String url;
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
