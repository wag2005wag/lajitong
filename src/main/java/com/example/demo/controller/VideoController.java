package com.example.demo.controller;

import com.example.demo.entity.Video;
import com.example.demo.service.VideoService;

import jakarta.servlet.http.HttpSession;

import com.example.demo.service.CrawlerService;
import com.example.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/Video")
public class VideoController {
    private VideoService videoService;
    private CrawlerService crawlerService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public VideoController(VideoService videoService, CrawlerService crawlerService) {
        this.videoService = videoService;
        this.crawlerService = crawlerService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> GetVideos()throws IOException {
        return ResponseEntity.ok(crawlerService.crawlbilibiliHomePage());
    }

    @GetMapping("/get")
    public ResponseEntity<List<Video>> GetVideo() {
        return ResponseEntity.ok(videoService.GetVideos());
    }

    @PostMapping("/upload")
public ResponseEntity<?> uploadImage(
        @RequestParam("title") String title,
        @RequestParam("file") MultipartFile file,
        HttpSession session
) {
    logger.info("收到上传请求");
    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("请选择文件");
    }

    try {
        // 获取项目根目录（更可靠的方法）
        String projectRoot = System.getProperty("user.dir");
        String uploadDir = projectRoot + File.separator + "uploads";
        
        // 创建目录（如果不存在）
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            boolean created = uploadPath.mkdirs();
            if (!created) {
                logger.error("无法创建上传目录: {}", uploadDir);
                return ResponseEntity.status(500).body("无法创建上传目录");
            }
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        File dest = new File(uploadPath, newFileName);
        file.transferTo(dest);

        // 构建URL时使用正确的路径
        String fileUrl = "http://localhost:7777/uploads/" + newFileName;
        String author=(String)session.getAttribute("username");
        videoService.updateVideos(title, author, fileUrl);
        return ResponseEntity.ok().body(Map.of("success", true, "imageUrl", fileUrl));
    } catch (IOException e) {
        logger.error("上传失败", e);
        return ResponseEntity.status(500).body("上传失败");
    }
}
}

class VideoRequest {
    private String title;
    private String author;
    private String picture;

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPicture() {
        return this.picture;
    }
}