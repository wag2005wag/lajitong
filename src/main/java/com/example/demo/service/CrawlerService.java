package com.example.demo.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.example.demo.entity.Video;
import org.springframework.stereotype.Service;
import com.example.demo.repository.VideoRepository;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CrawlerService {
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> crawlbilibiliHomePage() throws IOException{
        List<Video>videos=new ArrayList<>();
        String url="https://www.bilibili.com/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .referrer("https://www.bilibili.com/")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .timeout(10000) 
                .get();
        Elements videoItems=doc.select("div.feed-card");
        for(Element item:videoItems){
            try{
                String title=item.select("h3.bili-video-card__info--tit").text().trim();
                String author=item.select("span.bili-video-card__info--author").text().trim();
                String picture=item.select("img").attr("src");
                if(picture.startsWith("//")){
                    picture="https:"+picture;
                }
                Video video=new Video();
                video.setTitle(title);
                video.setAuthor(author);
                video.setPicture(picture);
                videos.add(video);
            }catch(Exception e){
                System.err.println("解析视频失败");
                continue;
            }
        }
        videoRepository.saveAll(videos);
        return videos;

    }
    
    
}
