package com.example.demo.controller;

import java.io.IOException;
import java.util.UUID;
import com.example.demo.entity.Passage;
import com.example.demo.entity.Video;
import com.example.demo.entity.favourite;
import com.example.demo.service.PassageService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
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
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

import com.example.demo.entity.Passage;
import com.example.demo.service.PassageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/Passage")

public class PassagesController {
    private PassageService passageService;
    private static final Logger logger = LoggerFactory.getLogger(PassageService.class);

    public PassagesController(PassageService passageService) {
        this.passageService=passageService;
    }
    @GetMapping("/getByUrl")
    public ResponseEntity<?> getPassage(@RequestParam("url") String url){
        logger.info("封面网址{}",url);
        String content=passageService.GetPassageByUrl(url).getContent();
        return ResponseEntity.ok().body(Map.of("success", true, "content", content));
    }


    @GetMapping("/get")
    public ResponseEntity<List<Passage>> GetPassage() {
        return ResponseEntity.ok(passageService.GetPassage());
    }

    @PostMapping("/upload")
public ResponseEntity<?> uploadImage(
        @RequestParam("title") String title,
        @RequestParam("file") MultipartFile file,
        @RequestParam("passage")MultipartFile txtfile,
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
        String passage=new String(txtfile.getBytes());
        passageService.updateVideos(title, author, fileUrl,passage);
        return ResponseEntity.ok().body(Map.of("success", true, "imageUrl", fileUrl));
    } catch (IOException e) {
        logger.error("上传失败", e);
        return ResponseEntity.status(500).body("上传失败");
    }
}
}
