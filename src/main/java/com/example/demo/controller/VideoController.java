package com.example.demo.controller;
import com.example.demo.entity.Video;
import com.example.demo.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Video")
public class VideoController {
    private VideoService videoService;
    
    public VideoController(VideoService videoService){
        this.videoService=videoService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> GetVideos(){
        return ResponseEntity.ok(videoService.GetVideos());
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
