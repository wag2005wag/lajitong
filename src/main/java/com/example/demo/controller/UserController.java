package com.example.demo.controller;

import com.example.demo.entity.User;
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
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        if (userService.login(request.getUsername(), request.getPassword())) {
            session.setAttribute("username", request.getUsername());
            logger.info("用户 {} 登录成功", request.getUsername());
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assign(@ModelAttribute LoginRequest request) {
        logger.info("收到注册请求: {}", request.getUsername());
        
        if (request.getFile() == null || request.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body("请选择头像图片");
        }

        try {
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
            File uploadPath = new File(uploadDir);
            
            if (!uploadPath.exists()) {
                if (!uploadPath.mkdirs()) {
                    logger.error("无法创建上传目录: {}", uploadDir);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
                }
            }

            String originalFilename = request.getFile().getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileExtension;

            File dest = new File(uploadPath, newFileName);
            request.getFile().transferTo(dest);

            String fileUrl = "http://localhost:7777/uploads/" + newFileName;
            userService.assign(request.getUsername(), request.getPassword(), fileUrl);
            
            return ResponseEntity.ok(Map.of("success", true, "message", "注册成功", "avatarUrl", fileUrl));
        } catch (IOException e) {
            logger.error("文件上传失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        } catch (Exception e) {
            logger.error("注册失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("注册失败: " + e.getMessage());
        }
    }

    @GetMapping("/getUserPicture")
    public ResponseEntity<?> getUserPictrue(HttpSession session){
        logger.info("收到获取用户头像请求");
        String username=(String)session.getAttribute("username");
        String url=userService.getUserPictrue(username);
        return ResponseEntity.ok(Map.of("success",true,"url",url));
    }
}

class LoginRequest {
    private String username;
    private String password;
    private MultipartFile file;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}