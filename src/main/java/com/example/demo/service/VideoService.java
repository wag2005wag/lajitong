package com.example.demo.service;

import com.example.demo.entity.Video;
import org.springframework.stereotype.Service;
import com.example.demo.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class VideoService {
    private VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> GetVideos() {
        return videoRepository.findAll();
    }
}