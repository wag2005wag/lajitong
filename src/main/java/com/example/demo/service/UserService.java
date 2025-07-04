package com.example.demo.service;

import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public boolean login(String username,String password){
        
        User user=userRepository.findUser(username);
        if (user != null) {
            logger.info("数据库中存储的密码: {}", user.getPassword());
            logger.info("用户输入的密码: {}", password);
        }
        if(user==null)return false;
        if(user.getPassword().equals(password))logger.info("用户输入的密码: {}", password);
        return user.getPassword().equals(password);
    }

    
}
