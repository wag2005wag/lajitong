package com.example.demo.service;

import com.example.demo.entity.Passage;
import org.springframework.stereotype.Service;
import com.example.demo.repository.PassageRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class PassageService {
    private PassageRepository passageRepository;

    public PassageService(PassageRepository passageRepository) {
        this.passageRepository = passageRepository;
    }

    public List<Passage> GetPassage() {
        return passageRepository.findAll();
    }

    public void updateVideos(String title,String author,String picture,String content){
        passageRepository.insertVideo(title,author,picture,content);
    }

    public Passage GetPassageByUrl(String url){
        return passageRepository.GetVideo(url);
    }
}