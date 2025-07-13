package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="select * from user where user.username=:username", nativeQuery = true)
    User findUser(@Param("username") String username);
    @Modifying
    @Transactional
    @Query(value="insert into user(username,password) values(:username,:password)",nativeQuery = true)
    void insertUser(@Param("username")String username,@Param("password")String password);
}