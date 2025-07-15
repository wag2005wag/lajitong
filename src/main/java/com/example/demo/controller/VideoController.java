package com.example.demo.controller;
import com.example.demo.entity.Video;
import com.example.demo.service.VideoService;
import com.example.demo.service.CrawlerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/Video")
public class VideoController {
    private VideoService videoService;
    private CrawlerService crawlerService;
    
    public VideoController(VideoService videoService,CrawlerService crawlerService){
        this.videoService=videoService;
        this.crawlerService=crawlerService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> GetVideos()throws IOException{
        return ResponseEntity.ok(crawlerService.crawlbilibiliHomePage());
    }
    @PostMapping("/update")
    public ResponseEntity<String> uploadVideos(@RequestBody VideoRequest request){
        videoService.updateVideos(request.getTitle(), request.getAuthor(), request.getPicture());
        return ResponseEntity.ok("upload video success");
    }
}

class VideoRequest{
    private String title;
    private String author;
    private String picture;

    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getPicture(){
        return this.picture;
    }


}
