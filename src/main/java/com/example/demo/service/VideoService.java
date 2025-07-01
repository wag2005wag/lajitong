package com.example.demo.service;

import com.example.demo.entity.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VideoService {
    private List<Video>Videos=new ArrayList<>();
    public VideoService(){
        Videos.add(new Video("src/assets/logo.png","bika","bika"));
        Videos.add(new Video("src/assets/logo.png","kila","kila"));
    }

    public List<Video> GetVideos(){
        return Videos;
    }
}
