package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import java.util.List;
@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request,HttpSession session) {  // 使用 @RequestBody
        if (userService.login(request.getUsername(), request.getPassword())) {
            session.setAttribute("username", request.getUsername());
            logger.info("{}",(String)session.getAttribute("username"));
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
    @PostMapping("/assign")
    public ResponseEntity<String> assign(@RequestBody LoginRequest request){
        if (userService.assign(request.getUsername(), request.getPassword())) {
            return ResponseEntity.ok("Assign successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Assign failed");
        }
    }
}

class LoginRequest {
    private String username;
    private String password;

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
